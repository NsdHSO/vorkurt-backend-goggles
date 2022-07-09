package com.vorkurt.service.pack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.response.PackResponse;
import com.vorkurt.entity.transport.pack.response.PackResponseData;
import com.vorkurt.repository.transport.pack.PackRepository;
import com.vorkurt.repository.transport.pack.address.PackageAddressRepository;
import com.vorkurt.repository.transport.pack.description.DescriptionRepository;
import com.vorkurt.repository.transport.pack.format.FormatPckRepository;
import com.vorkurt.repository.transport.pack.refound.RefoundRepository;

@Service
public class PackService {

	Logger logger = LoggerFactory.getLogger(PackService.class);

	@Autowired
	PackRepository packRepository;

	@Autowired
	DescriptionRepository descriptionRepository;

	@Autowired
	PackageAddressRepository packageAddressRepository;

	@Autowired
	FormatPckRepository formatPckRepository;

	@Autowired
	RefoundRepository refoundRepository;

	@Autowired
	PackageAddressRepository addressRepository;

	/**
	 * Retrieve all packs
	 *
	 * @return List of type Pack.
	 */
	public PackResponseData getAllPacks(Integer pageNo, Integer pageSize, String sortById) {

		logger.debug("Get All Pack");
		PackResponseData response = new PackResponseData();
		List<PackResponse> packResponses = new ArrayList<>();

		Pageable pagingPacks = PageRequest.of(pageNo, pageSize, Sort.by(sortById));

		Page<Pack> pageResult = this.packRepository.findAll(pagingPacks);

		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(pack -> {
				_setIsRepayment(!pack.isRepayment(), false, pack);
				packResponses.add(new PackResponse(pack));
			});
		}

		response.setEntries(packResponses);
		response.setCountEntries(((List<Pack>) packRepository.findAll()).size());
		return response;

	}

	public PackResponse getPacksByCarId(Long carId) {
		Car car = new Car();
		car.setId(carId);
		Pack daoPacks = packRepository.findById(carId).get();
		
		return new PackResponse(daoPacks);

	}

	/***
	 * @param id
	 * @param objectPack Return object from Dao
	 */
	public ResponseEntity<Pack> modifiedPack(long id, Pack objectPack, SessionStatus status) {
		Pack pacDao = this.packRepository.findById(id).orElseThrow(() -> new OpenApiResourceNotFoundException("Pack not found:: " + id));
		_setPropertiesOnPackDao(objectPack, pacDao);

		final Pack updatedPackResponse =  this.packRepository.save(pacDao);
		
		
		status.setComplete();
		return ResponseEntity.ok(updatedPackResponse);

	}

	/***
	 * @param pacDao from database not return nothing but set object properties in
	 *               pacDao
	 */
	public PackResponse addPack(Pack objectPack) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd, hh:mm");
		Pack pacDao = objectPack;
		pacDao.setTakenDate(dateFormat.format(new Date()));
		this.packRepository.save(pacDao);

		return new PackResponse(objectPack);
	}

	/***
	 * @param objectPack is from request
	 * @param pacDao     from database not return nothing but set object properties
	 *                   in pacDao
	 */
	private void _setPropertiesOnPackDao(Pack objectPack, Pack pacDao) {
		_setPackAddress(objectPack, pacDao);
		_setRefoundType(objectPack, pacDao);
		_setConsignee(objectPack, pacDao);
		_setIsRepayment(objectPack.isRepayment(), false, pacDao);
		_setgetTypeBox(objectPack, pacDao);
		_setDescription(objectPack, pacDao);
	}

	/***
	 * @param objectPack is from request
	 * @param pacDao     from database
	 *
	 */
	private void _setPackAddress(Pack objectPack, Pack pacDao) {
		if (objectPack.getPackageAddress() != null) {
			pacDao.getPackageAddress().setCity(objectPack.getPackageAddress().getCity());
			pacDao.getPackageAddress().setNumber(objectPack.getPackageAddress().getNumber());
			pacDao.getPackageAddress().setPostalCode(objectPack.getPackageAddress().getPostalCode());
			pacDao.getPackageAddress().setStreet(objectPack.getPackageAddress().getStreet());
			
		}
	}

	/***
	 * @param objectPack is from request
	 * @param pacDao     from database
	 */
	private void _setDescription(Pack objectPack, Pack pacDao) {
		if (objectPack.getDescription() != null) {
			pacDao.getDescription().setNote(objectPack.getDescription().getNote());
		}
	}

	/***
	 * @param objectPack is from request
	 * @param pacDao     from database
	 */
	private void _setgetTypeBox(Pack objectPack, Pack pacDao) {
		if (objectPack.getTypeBox() != null) {
			pacDao.getTypeBox().setBox(objectPack.getTypeBox().isBox());
			pacDao.getTypeBox().setEnvelop(objectPack.getTypeBox().isEnvelop());
			pacDao.getTypeBox().setTypePck(objectPack.getTypeBox().getTypePck());
		}
	}

	/***
	 * @param objectPack is from request
	 * @param pacDao     from database
	 */
	private void _setIsRepayment(boolean objectPack, boolean objectPack2, Pack pacDao) {
		pacDao.setRepayment(objectPack);
	}

	/***
	 * @param objectPack is from request
	 * @param pacDao     from database
	 */
	private void _setConsignee(Pack objectPack, Pack pacDao) {
		if (objectPack.getConsignee() != null) {
			pacDao.getConsignee().setApartment(objectPack.getConsignee().getApartment());
			pacDao.getConsignee().setCity(objectPack.getConsignee().getCity());
			pacDao.getConsignee().setNumber(objectPack.getConsignee().getNumber());
			pacDao.getConsignee().setPostalCode(objectPack.getConsignee().getPostalCode());
			pacDao.getConsignee().setStreet(objectPack.getConsignee().getStreet());
		}
	}

	/***
	 * @param objectPack is from request
	 * @param pacDao     from database
	 */
	private void _setRefoundType(Pack objectPack, Pack pacDao) {
		if (objectPack.getRefoundType() != null) {
			pacDao.setRefoundType(objectPack.getRefoundType());

		}
	}
}
