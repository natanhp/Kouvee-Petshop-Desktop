package main.controller;

public abstract class AbstractController {
    protected LoginController loginController;
    protected CustomerSecondaryController customerController;
    protected EmployeeController employeeController;
    protected MainMenuController mainMenuController;
    protected PetSecondaryController petController;
    protected PetSizeController petSizeController;
    protected ProductController ProductController;
    protected ServiceController serviceController;
    protected SupplierController supplierController;

    public void setTargetApp(LoginController loginController) {
        this.loginController = loginController;
        this.customerController = customerController;
        this.employeeController = employeeController;
        this.mainMenuController = mainMenuController;
        this.petController = petController;
        this.petSizeController = petSizeController;
    }
}
