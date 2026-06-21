package Autosarthi.demo.dto;

public class ServiceResponseDTO {

    private Long id;
    private String serviceName;
    private String description;
    private Double price;
    private String garageName;
    private String phone;
    private String city;

    public ServiceResponseDTO(Long id,
                              String serviceName,
                              String description,
                              Double price,
                              String garageName,
                              String phone,
                              String city) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.garageName = garageName;
        this.phone = phone;
        this.city = city;
    }

    public Long getId() { return id; }
    public String getServiceName() { return serviceName; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public String getGarageName() { return garageName; }
    public String getPhone() { return phone; }
    public String getCity() { return city; }
}
