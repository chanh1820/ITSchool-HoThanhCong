package com.nmc.itschool.dto;

public class MedicineDTO {
    private Long id;
    private String medicineName;
    private String medicineCode;
    private String description;
    private String dosageForm;
    private String targetUser;
    private String target;
    private String notTarget;
    private String usageDescribe;
    private String sideEffect;
    private String imageFile;
    private String categoryName;


    public MedicineDTO() {
    }

    public MedicineDTO(String medicineName) {
        this.medicineName = medicineName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNotTarget() {
        return notTarget;
    }

    public void setNotTarget(String notTarget) {
        this.notTarget = notTarget;
    }

    public String getUsageDescribe() {
        return usageDescribe;
    }

    public void setUsageDescribe(String usageDescribe) {
        this.usageDescribe = usageDescribe;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
