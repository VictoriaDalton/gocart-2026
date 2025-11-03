// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveTrain.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends SubsystemBase {

  private final WPI_TalonSRX m_leftMaster = new WPI_TalonSRX(kLeftMasterId);
  private final WPI_TalonSRX m_rightMaster = new WPI_TalonSRX(kRightMasterId);
  private final WPI_TalonSRX m_leftFollowerOne = new WPI_TalonSRX(kLeftFollowerOneId);
  private final WPI_TalonSRX m_leftFollowerTwo = new WPI_TalonSRX(kLeftFollowerTwoId);
  private final WPI_TalonSRX m_rightFollowerOne = new WPI_TalonSRX(kRightFollowerOneId);
  private final WPI_TalonSRX m_rightFollowerTwo = new WPI_TalonSRX(kRightFollowerTwoId);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMaster, m_rightMaster);

  /** Creates a new DriveTrain. */
  public DriveTrain() {


    m_leftMaster.configFactoryDefault();
    m_rightMaster.configFactoryDefault();
    m_leftFollowerOne.configFactoryDefault();
    m_rightFollowerOne.configFactoryDefault();
    m_leftFollowerTwo.configFactoryDefault();
    m_rightFollowerTwo.configFactoryDefault();

    m_leftMaster.setInverted(true);
    m_leftFollowerOne.setInverted(true);
    m_leftFollowerTwo.setInverted(true);
    m_rightMaster.setInverted(false);
    m_rightFollowerOne.setInverted(false);
    m_rightFollowerTwo.setInverted(false);

    m_leftFollowerOne.follow(m_leftMaster);
    m_leftFollowerTwo.follow(m_leftMaster);
    m_rightFollowerOne.follow(m_rightMaster);
    m_rightFollowerTwo.follow(m_rightMaster);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Open loop drive with arcade style inputs
   * @param forward [-1 .. 1] positive is forward
   * @param rotation [-1 .. 1] positive is counter-clockwise
   */
  public void tankDrive(double right, double left) {

    // Flip turn axis because arcadeDrive is not NWU compliant
    m_drive.tankDrive(left, right);
  }
}
