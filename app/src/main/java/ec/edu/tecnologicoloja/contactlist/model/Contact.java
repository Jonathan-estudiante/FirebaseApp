package ec.edu.tecnologicoloja.contactlist.model;


import java.util.Date;

public class Contact {
    private Date updatedAt;
    private String birthday;
    private String phone;
    private String imageUrl;
    private String email;
    private String objectId;
    private String description;
    private Date createdAt;
    private String name;
    private String gender;
    private String country;
    private String city;
    private GeoLocation location;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }
}
