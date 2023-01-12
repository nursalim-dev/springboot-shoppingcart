package co.id.bvk.springbootshoppingcart.service;

import co.id.bvk.springbootshoppingcart.entity.Item;
import co.id.bvk.springbootshoppingcart.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }

    public Item update(Item item) {
        Item updatedItem = itemRepository.save(item);
        return updatedItem;
    }

    public void delete(Item item){
        itemRepository.delete(item);
    }




}
