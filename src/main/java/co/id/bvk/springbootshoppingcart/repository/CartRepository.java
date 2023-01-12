package co.id.bvk.springbootshoppingcart.repository;

import co.id.bvk.springbootshoppingcart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
