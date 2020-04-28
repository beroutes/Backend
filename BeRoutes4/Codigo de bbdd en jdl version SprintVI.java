entity Region {
	id Integer,
	regionName String
}
entity City {
	id Integer,
	cityName String
}

entity Country {
	id Integer,
	countryName String,
	regionId Integer,
	cityId Integer
}

entity Location {
	id Integer,
	travelRouteId Integer,
	stepPosition Integer,
	titleLocation String,
	descriptionLocation String,	
    xCoordinate Double,
    yCoordinate Double,
    stepDuration String,
    durationId Integer,    
    qrId Integer,
    qrDescription String,
    createdAt LocalDate, 
    updatedAt LocalDate,
    photoId Integer,
    countryId Integer,
}

entity TravelRoute {
	id Integer,
	titleRoute String ,
    destination String,
    locationId Integer,
    season Season,
    budget Double,
    durationSummary String,
    durationId Integer,
    descriptionRouteSummary String, 
    descriptionRoute String, 
    createdAt LocalDate, 
    updatedAt LocalDate,
    categoryId Integer,
    userProfileId Integer
  
    
}

entity UserProfile {
	id Integer,
	firstName String,
	lastName String,
	email String  ,
    telephone Integer,
    userName String  ,
	password String ,
	age Integer,
    biography String,
    countryId Integer,
    createdAt LocalDate, 
    updatedAt LocalDate,
    photoId Integer
}
entity Follower{
	id Integer,
	accepted Boolean,
	followerId Integer,
	userProfileId Integer
}
entity Duration {
	id Integer,
   	descriptionDuration String,
    minutes Integer,
    hours Integer,
    days Integer,
    weeks Integer,
    years Integer,
}

entity Category {
	id Integer,
	nameCategory String,
	cheap Boolean, 
    luxury Boolean,
    lonely Boolean, 
    friends Boolean, 
    romantic Boolean, 
    kids Boolean,
    sport Boolean, 
    relaxation Boolean, 
    art Boolean, 
    food Boolean, 
    nature Boolean, 
    city Boolean
}

entity Photo {
	id Integer,
	titlePhoto String,
	descriptionPhoto String,
    urlPhoto String,
    travelRouteId Integer,
    imageRoute Blob
}

entity Valuation {
	id Integer,
	comment String,
	rating Integer,
	travelRouteId Integer,
	userProfileId Integer
}

entity Qr {
	id Integer,
	data1 Double,
	data2 Double,
	data3 Double
}





enum Season {
	SPRING, SUMMER, AUTUMN, WINTER
}

relationship OneToOne {
	Country{region(id)} to Region {country(regionId)}
}
relationship OneToOne {
	Country{city(id)} to City {country(cityId)}
}
relationship OneToOne {
	Location{duration(id)} to Duration{location(durationId)}
}

relationship OneToOne {
	Location{country(id)} to Country{location(countryId)}
}
relationship OneToOne {
	Location{photo(id)} to Photo {location(photoId)}
}

relationship OneToMany {
	TravelRoute{location(travelRouteId)} to Location{travelRoute(id)}
}

relationship OneToOne {
	TravelRoute{duration(id)} to Duration {travelRoute(durationId)}
}

relationship OneToOne {
	TravelRoute{category(id)} to Category {travelRoute(categoryId)}
}

relationship OneToMany {
	TravelRoute{photo(travelRouteId)} to Photo{travelRoute(id)}
}

relationship OneToMany {
	TravelRoute{valuation(travelRouteId)} to Valuation{travelRoute(id)}
}

relationship OneToMany {
	UserProfile{travelRoute(userProfileId)} to TravelRoute{userProfile(id)}
}

relationship OneToOne {
	UserProfile{country(id)} to Country{userProfile(countryId)}
}

relationship OneToOne {
	UserProfile{photo (id)} to Photo {userProfile(photoId)}
}

relationship OneToMany {
	UserProfile{valuation(userProfileId)} to Valuation{userProfile(id)}
}


relationship OneToMany {
	UserProfile{follower(userProfileId)} to Follower{userProfile(id)}
}

relationship OneToOne {
	 Location{qr(id)} to Qr{location (qrId)}
} 

////

// Set pagination options

paginate all with pagination

// Use Data Transfert Objects (DTO)
// dto * with mapstruct

// Set service options to all except few
service all with serviceImpl 

// Set an angular suffix
// angularSuffix * with mySuffix