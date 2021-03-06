/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Drivetrain for the kitbot.
 */
@Deprecated
public class KitDriveSubsystem extends SubsystemBase {
  
  // the talons that are in the drive train
  private WPI_TalonSRX
    leftFront, leftBack,
    rightFront, rightBack;
  
  // a group that allows for referencing both talons on a side as one side of the robot
  private SpeedControllerGroup leftSide, rightSide;

  // the whole drivetrain as one object
  private DifferentialDrive driveTrain;

  public KitDriveSubsystem() {
    // Create the drivetrain with talons

    leftFront = new WPI_TalonSRX(RobotMap.ID_FRONTLEFT);
    leftBack = new WPI_TalonSRX(RobotMap.ID_BACKLEFT);
    rightFront = new WPI_TalonSRX(RobotMap.ID_FRONTRIGHT);
    rightBack = new WPI_TalonSRX(RobotMap.ID_BACKRIGHT);

    leftSide = new SpeedControllerGroup(leftFront, leftBack);
    rightSide = new SpeedControllerGroup(rightFront, rightBack);
    
    leftSide.setInverted(false);
    rightSide.setInverted(false);

    driveTrain = new DifferentialDrive(leftSide, rightSide);
  }

  /**
   * Drives the left and right sides of the drivetrain independently of each other.
   * Note: Squares inputs for more control by human driver.
   * 
   * @param left Left side speed as a decimal percentage; from -1 to 1.
   * @param right Right side speed as a decimal percentage; from -1 to 1.
   */
  public void tankDriveHuman(double left, double right) {
    driveTrain.tankDrive(left, right, true);
  }

  /**
   * Drives the left and right sides of the drivetrain independently of each other.
   * Note: used by autonomous modes.
   * 
   * @param left Left side speed as a decimal percentage; from -1 to 1.
   * @param right Right side speed as a decimal percentage; from -1 to 1.
   */
  public void tankDriveRaw(double left, double right) {
    driveTrain.tankDrive(left, right, false);
  }

  /**
   * Drive using a forwards amount an a rotational amount.
   * 
   * @param x Forwards/Backwards speed; from -1 to 1.
   * @param z Clockwise/Counterclockwise speed; from -1 to 1.
   */
  public void arcadeDriveRaw(double x, double z) {
    driveTrain.arcadeDrive(x, z, false);
  }

  /**
   * Commands the entire drivetrain to stop all motors.
   */
  public void stop() {
    driveTrain.stopMotor();
  }

  @Override
  public void periodic() {
    
  }
}
