package org.example.kirana_register_project.crud.service;

import org.example.kirana_register_project.crud.model.Balance;
import org.example.kirana_register_project.crud.repository.BalanceRepository;
import org.example.kirana_register_project.crud.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public float getUserAssetBalance(UUID userID, String asset_type){
        return balanceRepository.findByUserAssetBalance(userID, asset_type);
    }

    public Balance CreateUserAssetBalance(Balance balance){
       return balanceRepository.save(balance);
    }

    public void UpdateUserAssetBalance(UUID userID, String asset_type, float newBalance){
        balanceRepository.updateBalance(userID, asset_type, newBalance );
    }

}
