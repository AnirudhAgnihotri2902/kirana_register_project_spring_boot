package org.example.kirana_register_project.crud.controller;


import org.example.kirana_register_project.crud.model.Balance;
import org.example.kirana_register_project.crud.model.Users;
import org.example.kirana_register_project.crud.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/users/balance/{id}/{assetType}")
    public float queryUserBalance(@PathVariable(value = "id") UUID userId,
    @PathVariable(value = "assetType") String assetType) {{
            return balanceService.getUserAssetBalance(userId, assetType);
        }
    }

    @PostMapping("/users/balance")
    public Balance createUserBalance(@Valid @RequestBody Balance balance) {
        return balanceService.CreateUserAssetBalance(balance);
    }

    @PatchMapping("/users/balance")
    public void updateUserBalance(@Valid @RequestBody Balance balance) {
        balanceService.UpdateUserAssetBalance(balance.getUserID(), balance.getAssetType(), balance.getBalance());
    }



}
