pipeline {
  agent {
    label 'karan-webserver'
  }
  stages {
    stage('Pull') {
      steps {
       git 'https://github.com/learnerkaran/studentapp-ui.git'
       echo 'Clone and Pull complete'
      }
    }
    stage('Build') {
      steps {
        sh '''
        sudo mvn clean package
        sudo apt update
        sudo apt install unzip -y
        sudo curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.96/bin/apache-tomcat-9.0.96.zip
        sudo unzip apache-tomcat-9.0.96'''
        echo 'Build complete'
      }
    }
    stage('Test') {
      steps {
       echo 'we are testing using sonarqube'
      }
    }
    stage('Deploy') {
      steps {
        sh '''sudo mv target/*.war apache-tomcat-9.0.96/webapps/student.war
        sudo bash apache-tomcat-9.0.96/bin/catalina.sh start'''
        echo 'deployment complete'
      }
    }
  }
}