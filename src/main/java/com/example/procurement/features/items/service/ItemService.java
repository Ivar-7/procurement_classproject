package com.example.procurement.features.items.service;

import com.example.procurement.features.items.dao.ItemDao;
import com.example.procurement.features.items.model.Item;

import java.util.List;

public class ItemService {

    private final ItemDao itemDao = new ItemDao();

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Item getItemById(int itemId) {
        return itemDao.findById(itemId);
    }

    public void createItem(Item item) {
        itemDao.create(item);
    }

    public boolean updateItem(Item item) {
        return itemDao.update(item);
    }

    public boolean deleteItem(int itemId) {
        return itemDao.deleteById(itemId);
    }
}
