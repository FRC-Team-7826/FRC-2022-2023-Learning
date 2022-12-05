// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.*;

// PhotonLib
import org.photonvision.*;
import org.photonvision.targeting.*;
// NetworkTables
import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GetLimelightValues extends SubsystemBase {
  /** Creates a new subsystem. */
  public GetLimelightValues() {}

  // NetworkTables
  // Get the default instance of NetworkTables that was created automatically when your program starts
  NetworkTableInstance NTinst = NetworkTableInstance.getDefault();
  // Get the table within that instance that contains the data. There can be as many tables as you like and exist to make it easier to organize your data.
  NetworkTable PVtable = NTinst.getTable("photonvision");

  // PhotonLib
  PhotonCamera camera = new PhotonCamera("gloworm");

  @Override
  public void periodic() { // This method will be called once per scheduler run

    // NetworkTables
    NetworkTableEntry NTtarget = PVtable.getEntry("targetPose");
    System.out.println("From PhotonVision: " + NTtarget);
    
    // PhotonLib
    // Query the latest result from PhotonVision
    PhotonPipelineResult result = camera.getLatestResult();
    // Check if the latest result has any targets.
    boolean hasTargets = result.hasTargets();
    // Get a list of currently tracked targets.
    List<PhotonTrackedTarget> targets = result.getTargets();
    // Get the current best target.
    PhotonTrackedTarget PVtarget = result.getBestTarget();
    System.out.println("From NetworkTables: " + PVtarget);
  }

  @Override
  public void simulationPeriodic() { // This method will be called once per scheduler run during simulation


  }
}
