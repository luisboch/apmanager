package com.apmanager.service.validator;

import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.ProductKeyWord;
import com.apmanager.service.annotations.Validator;
import com.apmanager.service.base.ActionType;
import com.apmanager.service.base.EntityValidator;
import com.apmanager.service.base.exception.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Luis
 */
@Validator(target = Product.class)
public class ProductValidator implements EntityValidator<Product> {

    public void validate(Product newEntity, Product oldEntity, ActionType type) {
        if (type.equals(ActionType.DELETE)) {
            throw new RuntimeException("Can't delete this item");
        }

        final ValidationException ex = new ValidationException();

        if (newEntity.getName() == null || newEntity.getName().equals("")) {
            ex.addError("Invalid name", "name", "invalid.name");
        }

        if (newEntity.getDescription() == null || newEntity.getDescription().equals("")) {
            ex.addError("Invalid description", "description", "invalid.description");
        }

        if (newEntity.getBrand() == null) {
            ex.addError("Invalid brand", "brand", "invalid.brand");
        }

        if (newEntity.getCode() == null || newEntity.getCode().equals("")) {
            ex.addError("Invalid code", "code", "invalid.code");
        }

        if (newEntity.getBarcode() == null || newEntity.getBarcode().equals("")) {
            ex.addError("Invalid barcode", "barcode", "invalid.barcode");
        }

        if (newEntity.getPurchuasePrice() == null) {
            ex.addError("Invalid purchuase price", "purchuasePrice", "invalid.purchuase.price");
        }

        if (newEntity.getSellPrice() == null) {
            ex.addError("Invalid sell price", "purchuasePrice", "invalid.sell.price");
        }

        if (newEntity.getQuantity() == null) {
            ex.addError("Invalid quantity", "quantity", "invalid.quantity");
        }

        if (newEntity.getMinQuantity() == null) {
            ex.addError("Invalid min quantity", "minQuantity", "invalid.min.quantity");
        }

        if (newEntity.getShelf() == null) {
            ex.addError("Invalid shelf", "shelf", "invalid.shelf");
        }

        if (newEntity.getUserKeyWords() == null || newEntity.getUserKeyWords().isEmpty()) {
            ex.addError("Invalid User Key Words", "userKeyWords", "invalid.user.keywords");
        }

        if (newEntity.getKeywords() == null) {
            newEntity.setKeywords(new ArrayList<ProductKeyWord>());
        }
        
        if(newEntity.getMaxDiscountPercent() == null){
            newEntity.setMaxDiscountPercent(0);
        }

        if (!ex.isEmpty()) {
            throw ex;
        }
        //Update keyWords
        updateKeyWords(newEntity.getKeywords(), newEntity);

    }

    private void updateKeyWords(List<ProductKeyWord> list, Product p) {
        list.clear();

        addKeyWord(list, p, p.getName());
        addKeyWord(list, p, p.getDescription());
        addKeyWord(list, p, p.getBrand().getName());
        addKeyWord(list, p, p.getCode());
        addKeyWord(list, p, p.getBarcode());
        addKeyWord(list, p, p.getShelf().getCode());
        addKeyWord(list, p, p.getShelf().getDescription());
        addKeyWord(list, p, p.getUserKeyWords());
    }

    private void addKeyWord(List<ProductKeyWord> keywords, Product p, String str) {

        final List<String> words = new ArrayList();

        final List<String> pieces = Arrays.asList(str.split(" "));

        for (String s : pieces) {
            words.addAll(Arrays.asList(s.split(",")));
        }

        for (String w : words) {
            keywords.add(new ProductKeyWord(p, w.trim()));
        }

    }
}
