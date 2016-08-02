
package org.usfirst.frc.team4361.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    Talon[] talon;
    Joystick stick1;
    double stick1Y;
    double stick2Y;
    double L1;
    double L2;
    double shift;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        talon = new Talon[6];
        for(int i =0; i<talon.length; i++)
        {
        	talon[i]= new Talon(i);
        }
        stick1 = new Joystick(0);
        stick1Y=stick1.getRawAxis(1);
        stick2Y = stick1.getRawAxis(5);
        L1 = stick1.getRawAxis(2);
        L2 = stick1.getRawAxis(3);
        shift = .5;
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
      stick1Y=stick1.getRawAxis(1);
      stick2Y = stick1.getRawAxis(5);
      L1 = -stick1.getRawAxis(2);
      L2 = -stick1.getRawAxis(3);
      
      if(stick1.getRawButton(5))
      {
    	  talon[4].set(-L1);
    	  talon[5].set(-L2);
      }
      else
      {
    	  talon[4].set(L1);
    	  talon[5].set(L2);
      }
      /*if(stick1.getRawButton(6))
      {
    	  talon[5].set(-L2);
      }
      else
      {
    	  talon[5].set(L2);
      }*/
      if(stick1.getRawButton(1)&&shift>.25)
      {
    	  shift-=.25;
      }
      if(stick1.getRawButton(4)&&shift<=1)
      {
    	  shift+=.25;
      }
      
      talon[0].set(stick2Y*shift);
      talon[1].set(-stick1Y*shift);
      talon[2].set(stick2Y*shift);
      talon[3].set(-stick1Y*shift);
        
    }
  
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
