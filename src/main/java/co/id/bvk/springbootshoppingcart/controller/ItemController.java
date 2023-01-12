package co.id.bvk.springbootshoppingcart.controller;

import co.id.bvk.springbootshoppingcart.entity.Item;
import co.id.bvk.springbootshoppingcart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("")
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item newItem = itemService.create(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> findItemById(@PathVariable("id") Long id) {
        Item item = itemService.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") Long id, @RequestBody Item item){
        Item oldItem = itemService.findById(id);
        oldItem.setStock(item.getStock());
        oldItem.setName(item.getName());
        oldItem.setPrice(item.getPrice());

        Item updatedItem = itemService.update(oldItem);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
        Item item = itemService.findById(id);
        itemService.delete(item);
        return new ResponseEntity<>("Item has been successfully deleted", HttpStatus.OK);
    }




}
