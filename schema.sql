USE fitness_center_schema;

CREATE TABLE Languages (
	id varchar(36) not null unique,
    language_name varchar(20) not null unique,
    active boolean,
    PRIMARY KEY (id)
    );
    


    
CREATE TABLE Users (
	id varchar (36) not null unique,
    phone_number varchar(50) not null,
    address varchar(50) not null,
    first_name varchar (20) not null,
    last_name varchar(20) not null,
    password varchar(20) not null,
    email varchar(30) not null,
    credit_card_number varchar (20) not null,
    main_language varchar(36) not null,
    role varchar(10) not null,
    timezone_id varchar(50) not null,
    active boolean,
    PRIMARY KEY (id),
    FOREIGN KEY (main_language) REFERENCES Languages(id)
    );
    
    
CREATE TABLE UsersLanguages (
	user_id varchar(36) not null,
    language_id varchar(36) not null,
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (language_id) REFERENCES Languages (id)
    );
    

CREATE TABLE Reviews (
	id varchar(36) not null unique,
	submitted_id varchar(36) not null,
    target_id varchar(36) not null,
    rating int not null,
    txt varchar(200) not null,
    active boolean,
	PRIMARY KEY (id),
    FOREIGN KEY (submitted_id) REFERENCES Users(id),
    FOREIGN KEY (target_id) REFERENCES Users(id)
    );	
    

    
CREATE TABLE Trainers (
	id varchar (36) not null unique,
    diploma boolean not null,
    certificate varchar(200) not null,
    vocation varchar (100) not null,
    accepted boolean not null,
    FOREIGN KEY (id) REFERENCES Users(id)
    );
    
CREATE TABLE WatchData (
	id varchar(36) not null unique,
    heart_rate varchar(100) not null,
    calories float not null,
    PRIMARY KEY (id)
    );
    
    
    
CREATE TABLE ClientApplicationData (
	id varchar (36) not null unique,
    height float not null,
    weight float not null,
	PRIMARY KEY(id)
    );
    
CREATE TABLE Goals (
	application_id varchar(36) not null unique,
    goal varchar(20) not null,
    FOREIGN KEY (application_id) REFERENCES ClientApplicationData (id)
    );

CREATE TABLE Equipment (
	application_id varchar(36) not null unique,
    equipment varchar(20) not null,
    FOREIGN KEY (application_id) REFERENCES ClientApplicationData (id)
	);
    
CREATE TABLE HealthConditions (
	application_id varchar(36) not null unique,
    h_condition varchar(20) not null,
    FOREIGN KEY (application_id) REFERENCES ClientApplicationData (id)
    );
        
CREATE TABLE TrainingSession (
	id varchar(36) not null unique,
    start_time dateTime not null,
    duration int not null,
    status varchar(20) not null,
    trainer_id varchar(36) not null,
    client_id varchar(36),
    application_id varchar(36),
    watch_id varchar(36),
    price float,
    active boolean not null,
    PRIMARY KEY (id),
    FOREIGN KEY (trainer_id) REFERENCES Users(id),
    FOREIGN KEY (client_id) REFERENCES Users(id),
    FOREIGN KEY (application_id) REFERENCES ClientApplicationData(id),
    FOREIGN KEY (watch_id) REFERENCES WatchData(id)
    );
    
CREATE TABLE Billing (
	id varchar(36) not null unique,
	trainer_id varchar(36) not null,
    client_id varchar(36) not null,
    price float,
    PRIMARY KEY (id),
	FOREIGN KEY (trainer_id) REFERENCES Users(id),
    FOREIGN KEY (client_id) REFERENCES Users(id)
    );
    
CREATE TABLE Companies (
	id varchar(36) not null unique,
    c_name varchar(50) not null,
	credit_card_number varchar (20) not null,
    active boolean not null,
    PRIMARY KEY (id)
    );

INSERT INTO languages(id,language_name,active) VALUES ('l1','English',1);
INSERT INTO languages(id,language_name,active) VALUES ('l2','English',1);
INSERT INTO languages(id,language_name,active) VALUES ('l3','Spanish',1);

INSERT INTO Companies(id,c_name,credit_card_number,active) VALUES ('c1','FitnessCenter','FC03',1);

INSERT INTO Users(id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id) VALUES ('u1','555333','Jovana Stojana','Kraljevic','Marko','jakasifra','jaka@jaka.com','cc01','l1','ADMIN','Europe/Budapest');
INSERT INTO Users(id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id) VALUES ('u2','333333','Jovana Bojana','Trainer','Trainer','trainer','trainer@trainer.com','cc01','l1','TRAINER','Europe/Budapest');
INSERT INTO Users(id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id) VALUES ('u3','666333','Jovana Marka','Client','Client','client','client@client.com','cc01','l1','CLIENT','Europe/Budapest');

INSERT INTO UsersLanguages(user_id,language_id) VALUES ('u3','l1');
INSERT INTO UsersLanguages(user_id,language_id) VALUES ('u3','l2');
INSERT INTO UsersLanguages(user_id,language_id) VALUES ('u3','l3');

INSERT INTO reviews(id,submitted_id,target_id,rating,txt,active) VALUES ('r1','u3','u2',4,'Odlican trener',1);









    

