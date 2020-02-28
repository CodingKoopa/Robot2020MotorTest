/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs
 * the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX m_leftFrontMotor = new WPI_TalonSRX(1);
  private final WPI_VictorSPX m_leftBackMotor = new WPI_VictorSPX(2);
  private final WPI_TalonSRX m_rightFrontMotor = new WPI_TalonSRX(3);
  private final WPI_VictorSPX m_rightBackMotor = new WPI_VictorSPX(4);
  private final WPI_TalonSRX m_colorWheelMotor = new WPI_TalonSRX(6);
  private final WPI_TalonSRX m_intakeMotor = new WPI_TalonSRX(7);
  private final WPI_VictorSPX m_leftCannonMotor = new WPI_VictorSPX(8);
  private final WPI_TalonSRX m_rightCannonMotor = new WPI_TalonSRX(11);
  private final WPI_VictorSPX m_beltMotor = new WPI_VictorSPX(10);
  private final WPI_TalonSRX m_winchMotor = new WPI_TalonSRX(12);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftFrontMotor, m_rightFrontMotor);
  private final Joystick m_driveController = new Joystick(0);
  private final Joystick m_manipController = new Joystick(1);

  @Override
  public void robotInit() {
    // compressor.setClosedLoopControl(true);

    m_leftBackMotor.follow(m_leftFrontMotor);
    m_rightBackMotor.follow(m_rightFrontMotor);

    m_rightCannonMotor.follow(m_leftCannonMotor);
  }

  @Override
  public void teleopInit() {
    // compressor.start();
  }

  @Override
  public void teleopPeriodic() {

    if (m_driveController.getRawButton(6)) {
      m_beltMotor.set(1);
    } else if (m_driveController.getRawButton(5)) {
      m_beltMotor.set(-1);
    } else {
      m_beltMotor.set(0);
    }

    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(m_driveController.getY(), m_driveController.getX());

    if (m_driveController.getRawAxis(3) > 0.5) {
      m_winchMotor.set(1);
    } else {
      m_winchMotor.set(0);
    }

    if (m_manipController.getRawButton(1)) {
      m_colorWheelMotor.set(0.75);
    } else {
      m_colorWheelMotor.set(0);
    }

    if (m_manipController.getRawAxis(2) > 0.5) {
      m_intakeMotor.set(1);
    } else {
      m_intakeMotor.set(0);
    }

    if (m_manipController.getRawAxis(3) > 0.5) {
      m_leftCannonMotor.set(1);
    } else {
      m_leftCannonMotor.set(0);
    }
  }
}
