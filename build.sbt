name := "tiny08"

version := "1.0"

scalaVersion := "2.10.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M6-SNAP8" % "test"

unmanagedJars in Compile +=
    Attributed.blank(file("/Library/Java/JavaVirtualMachines/jdk1.7.0_17.jdk/Contents/Home/jre/lib/jfxrt.jar"))