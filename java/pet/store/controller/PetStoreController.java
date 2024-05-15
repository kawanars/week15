package pet.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;


@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("creating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}

	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, 
			@RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId); 
		log.info("Updating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId,
			@RequestBody PetStoreEmployee employee) {
		System.out.println("Adding employee to pet store: " + petStoreId);
		return petStoreService.saveEmployee(petStoreId, employee);
	}

	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer addCustomerToPetStore(@PathVariable Long petStoreId,
			@RequestBody PetStoreCustomer customer) {
		System.out.println("Adding customer to pet store: " + petStoreId);
		return petStoreService.saveCustomer(petStoreId, customer);
	}

	@GetMapping
	public List<PetStoreData> getAllPetStores() {
		return petStoreService.retrieveAllPetStores();
	}

	@GetMapping("/{petStoreId}")
	public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
		return petStoreService.retrievePetStoreById(petStoreId);
	}

	@DeleteMapping("/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		System.out.println("Deleted " + petStoreId);
		petStoreService.deletePetStoreById(petStoreId);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Pet store " + petStoreId + " deleted");
		return response;
	}

}