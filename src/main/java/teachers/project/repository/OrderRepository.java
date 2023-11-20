package teachers.project.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import teachers.project.entity.Order;
;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    ArrayList<Order> findByOrderDate(LocalDate term);





}
