package com.sha.springbootdeviceseller.service;

import com.sha.springbootdeviceseller.model.Purchase;
import com.sha.springbootdeviceseller.repository.PurchaseRepository;
import com.sha.springbootdeviceseller.repository.projection.PurchaseItem;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase savePurchase(Purchase purchase){
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }
    @Override
    public List<PurchaseItem> findPurchaseItemsOfUser(Long userId){
        return purchaseRepository.findAllPurchasesOfUser(userId);
    }
}
