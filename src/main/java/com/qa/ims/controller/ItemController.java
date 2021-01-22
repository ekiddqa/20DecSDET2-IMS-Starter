package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.IDomainDao;
import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.item;
import com.qa.ims.utils.JavaUtilities;


public class ItemController implements IDomainDao<Item>{
	//yelling because CRUD methods aren't final since they need to convert to doubles

	    public static final Logger LOGGER = LogManager.getLogger();

	    private ItemDao itemDao;
	    private JavaUtilities javaUtilities;

	    public ItemController(ItemDao itemDao, JavaUtilities javaUtilities) {
	        super();
	        this.itemDao = itemDao;
	        this.javaUtilities = javaUtilities;
	    }

	    @Override
	    public List<Item> readAll() {
	        List<Item> items = itemDao.readAll();
	        for (Item item : items) {
	            LOGGER.info(item);
	        }
	        return items;
	    }

	    @Override
	    public Item create() {
	        LOGGER.info("Please enter a product name");
	        String name = javaUtilities.getString();
	        LOGGER.info("Please enter the price");
	        String price = javaUtilities.getString();
	        LOGGER.info("Please enter the price");
	        String  = javaUtilities.getString();
	        //convert to double -will need to include try catch
	        Item item = itemDao.create(new Item(name, price));
	        LOGGER.info("item created");
	        return item;
	    }

	    @Override
	    public Item update() {
	        LOGGER.info("Please enter the id of the item you would like to update");
	        Long id = javaUtilities.getLong();
	        LOGGER.info("Please enter a first name");
	        String name = javaUtilities.getString();
	        LOGGER.info("Please enter a price");
	        String price = javaUtilities.getString();
	        //convert to double
	        Item item = itemDao.update(new Item(id, name, price));
	        LOGGER.info("item Updated");
	        return item;
	    }

	    @Override
	    public int delete() {
	        LOGGER.info("Please enter the id of the item you would like to delete");
	        Long id = javaUtilities.getLong();
	        return itemDao.delete(id);
	    }

}

