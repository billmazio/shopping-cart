package seminars.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seminars.project.entity.Order;


import java.time.LocalDate;
import java.util.ArrayList;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    ArrayList<Order> findByOrderDate(LocalDate term);





}
