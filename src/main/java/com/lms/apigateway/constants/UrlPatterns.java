package com.lms.apigateway.constants;

public interface UrlPatterns {

	static String USERS_SERVICE = "/api/v1/users-service/";
	static String FREIGHTS_SERVICE = "/api/v1/freights-service/";
	static String VEHICLES_SERVICE = "/api/v1/vehicles-service/";
	static String REFERENCE_DATA_SERVICE = "/api/v1/reference-data-service/";
	static String SUBSCRIPTIONS_SERVICE = "/api/v1/subscriptions-service/";
	static String SETTINGS_SERVICE = "/api/v1/settings-service/";
	static String DOCUMENTS_SERVICE = "/api/v1/documents-service/";
	static String FREIGHTS_SEARCH_SERVICE = "/api/v1/freights-search-service/";
	static String VEHICLES_SEARCH_SERVICE = "/api/v1/vehicles-search-service/";

	static String ADD_USER = "users";
	static String EDIT_USER = "users/{id}";
	static String DELETE_USER = "users/{id}";
	static String ADD_ROLE = "roles";
	static String EDIT_ROLE = "roles/{id}";
	static String DELETE_ROLE = "roles/{id}";

	static String ADD_FREIGHT = "freights";
	static String EDIT_FREIGHT = "freights/{id}";
	static String CHANGE_FREIGHT_PROPERTY = "freights/{id}";

	static String ADD_VEHICLE = "vehicles";
	static String EDIT_VEHICLE = "vehicles/{id}";
	static String CHANGE_VEHICLE_PROPERTY = "vehicles/{id}";
	static String DELETE_VEHICLE = "vehicles/{id}";

	static String ADD_MANUAL_OFFER = "manualOffers";
	static String EDIT_MANUAL_OFFER = "manualOffers/{id}";
	static String CHANGE_MANUAL_OFFER_PROPERTY = "manualOffers/{id}";

	static String GET_LOOKUPS = "lookups";

	static String GET_SUBSCRIBER_BY_ID = "subscriptions/{id}";
	static String ADD_SUBSCRIBER = "subscriptions";
	static String EDIT_SUBSCRIBER = "subscriptions/{id}";
	static String CHANGE_SUBSCRIBER_PROPERTY = "subscriptions/{id}";
	static String GET_ALL_COMPANY_NAMES = "subscriptions/companyNames";
	static String SUBSCRIBER_SEARCH = "subscriptions/search";

	static String GET_SETTINGS = "settings/{id}";
	static String EDIT_SETTINGS = "settings/{id}";
	static String GET_START_SETTINGS = "startsettings/{id}";

	static String GET_FILES_NAMES = "documents/types/{prefix}";
	static String UPLOAD_DOCUMENT = "documents/upload/{prefix}/{documentType}";
	static String DOWNLOAD_DOCUMENT = "documents/download/{prefix}/{documentType}";

	static String GET_FREIGHT_BY_ID = "freights/{freightId}";
	static String GET_SUBSCRIBER_FREIGHTS = "freights/subscription/{subscriberId}";
	static String GET_USER_FREIGHTS = "freights/user/{userId}";
	static String FREIGHT_SEARCH = "freights/search";

	static String GET_VEHICLE_BY_ID = "vehicles/{vehicleId}";
	static String GET_SUBSCRIBER_VEHICLES = "vehicles/subscription/{subscriberId}";
	static String GET_USER_VEHICLES = "vehicles/user/{userId}";
	static String VEHICLE_SEARCH = "vehiclesQuery/search";

	static String GET_MANUAL_OFFER_BY_ID = "manualOffers/{manualOfferId}";
	static String GET_SUBSCRIBER_MANUAL_OFFERS = "manualOffers/subscription/{subscriberId}";
	static String GET_USER_MANUAL_OFFERS = "manualOffers/user/{userId}";

}
