package com.example.procurement.features.items.service;

import com.example.procurement.features.items.dao.ItemDao;
import com.example.procurement.features.items.model.Item;

import java.util.List;

public class ItemService {

    private final ItemDao itemDao = new ItemDao();

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public void createItem(Item item) {
        itemDao.create(item);
    }
}
