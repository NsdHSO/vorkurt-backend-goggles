package com.vorkurt.controller.transport.pack;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.response.PackResponse;
import com.vorkurt.entity.transport.pack.response.PackResponseData;
import com.vorkurt.service.pack.PackService;

@RestController
@RequestMapping("/api/transport/pack")
@CrossOrigin
public class PackController {
	Logger logger = LoggerFactory.getLogger(PackController.class);

	@Autowired
	PackService packService;

	@GetMapping()
	public PackResponseData getAllPacks(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		logger.debug("Method get all packs");
		return this.packService.getAllPacks(pageNo, pageSize, sortBy);
	}

	@PutMapping("{id}")
	public ResponseEntity<Pack> modifiedPack(@PathVariable long id, @RequestBody Pack objModified, SessionStatus status) {
		logger.debug("Method modified pack");
		return this.packService.modifiedPack(id, objModified, status);
	}

	@GetMapping("{id}")
	public List<PackResponse> getPackById(@PathVariable long id) {
		logger.debug("Method Get Pack By ID");
		return this.packService.getPacksByCarId(id);
	}

	@PostMapping()
	public PackResponse addPackById(@RequestBody Pack newPach) {
		logger.debug("Method Added pack");
		return this.packService.addPack(newPach);
	}
}
