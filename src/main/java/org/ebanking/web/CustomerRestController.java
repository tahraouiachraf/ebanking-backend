package org.ebanking.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ebanking.dtos.CustomerDTO;
import org.ebanking.exceptions.CustomerNotFoundException;
import org.ebanking.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    @PreAuthorize("hashAuthority('SCOPE_USER')")
    public List<CustomerDTO> customers() {
        return bankAccountService.listCostomers();
    }

    @GetMapping("/customers/search")
    @PreAuthorize("hashAuthority('SCOPE_USER')")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword) {
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hashAuthority('SCOPE_USER')")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    @PreAuthorize("hashAuthority('SCOPE_ADMIN')")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCostomer(customerDTO);
    }

    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hashAuthority('SCOPE_ADMIN')")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hashAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }
}
