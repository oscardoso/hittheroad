package org.academiadecodigo.bootcamp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp.controller.Navigation;
import org.academiadecodigo.bootcamp.model.dao.hibernate.HibernateRoleDao;
import org.academiadecodigo.bootcamp.model.dao.hibernate.HibernateUserDao;
import org.academiadecodigo.bootcamp.persistence.hibernate.HibernateTransactionManager;
import org.academiadecodigo.bootcamp.persistence.jdbc.ConnectionManager;
import org.academiadecodigo.bootcamp.service.user.HibernateUserService;
import org.academiadecodigo.bootcamp.service.ServiceRegistry;
import org.academiadecodigo.bootcamp.service.user.UserService;
import org.academiadecodigo.bootcamp.service.user.UserServiceImpl;

public class Main extends Application {

    //private ConnectionManager connectionManager;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() {
        //connectionManager = new ConnectionManager();
        //UserService jservice = new JdbcUserService(connectionManager);
        //UserService hservice = new HibernateUserService();
        UserServiceImpl service = new UserServiceImpl();
        service.setTransactionManager(new HibernateTransactionManager());
        service.setUserDao(new HibernateUserDao());
        service.setRoleDao(new HibernateRoleDao());
        ServiceRegistry.getInstance().registerService(service);

    }

    @Override
    public void start(Stage primaryStage) {

        Navigation navigation = Navigation.getInstance();
        navigation.setStage(primaryStage);
        // load the first scene
        navigation.loadScreen("login");

    }

    public void close() {
        //connectionManager.close();
    }

}