package vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.models.*;
import vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.services.CustomerService;
import vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.services.EmployeeService;
import vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.services.OrderService;
import vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.services.interFaceService.IFCustomerService;
import vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.services.interFaceService.IFEmployeeService;
import vn.edu.iuh.fit.www_lab_week02_20014641_vonguyenthanhtu.services.interFaceService.IFOrderService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Path("/orders")
public class OrderResources {
    private final IFOrderService orderService;
    private final IFCustomerService customerService;

    private final IFEmployeeService employeeService;

    private Logger loggerFactory;
    private final Logger logger = loggerFactory.getLogger(this.getClass().getName());

    public OrderResources() {
        orderService = new OrderService();
        customerService = new CustomerService();
        employeeService = new EmployeeService();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Order createOrder(Order order){
        if (orderService.insert(order)){
            return order;
        }
        return null;
    }


//    @PUT
//    @Produces("application/json")
//    @Path("orders_info_cus/{id}")
//    public boolean updateOrderOfCus(Order order, @PathParam("id") long id){
//
//            Customer existingCustomer = customerService.get(id, Customer.class).orElse(null);
//            Order orderNew =  new Order(order.getOrderDate(), order.getEmployeee(), order.getCustomer(), order.getOrderDetails());
//
//        if (existingCustomer != null){
//                existingCustomer.addOrderOfCus(orderNew);
//                customerService.update(existingCustomer);
//                return true;
//            }
//
//        return false;
//    }


    @GET
    @Produces("application/json")
    public Response getAllResponse() {
        List<Order> entity = orderService.getAll(Order.class);
        return Response.ok(entity).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getOrder(@PathParam("id") long eid) {
        Optional<Order> order = orderService.get(eid, Order.class);
        if (order.isPresent()){
            return Response.ok(order.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}