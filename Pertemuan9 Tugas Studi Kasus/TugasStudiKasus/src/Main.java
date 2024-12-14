/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fahla
 */
import model.MyBatisUtil;
import model.EWasteMapper;
import org.apache.ibatis.session.SqlSession;
import view.EWasteView;
import controller.EWasteController;

public class Main {
    public static void main(String[] args) {
        // Get a MyBatis session
        SqlSession session = MyBatisUtil.getSqlSession();
        
        // Get the EWasteMapper to interact with the database
        EWasteMapper mapper = session.getMapper(EWasteMapper.class);

        // Create an instance of EWasteView (the updated view)
        EWasteView view = new EWasteView();
        
        // Initialize the controller with the view and mapper
        new EWasteController(view, mapper);

        // Set the view visible to display the application
        view.setVisible(true);
    }
}
