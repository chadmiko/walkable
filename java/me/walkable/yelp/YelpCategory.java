/**
 * 
 */
package me.walkable.yelp;

import java.util.ArrayList;

import me.walkable.util.CategoryTree;

/**
 * @author Christopher Butera
 *
 */
public class YelpCategory {

	public CategoryTree yelpCategories;
	
	public ArrayList<CategoryTree> getCategories(){
		return yelpCategories.getSubCategories();
	}
	
	public YelpCategory(){
		yelpCategories = new CategoryTree("");
//ACTIVE
		CategoryTree active = yelpCategories.addSubCategory("active");
		active.addSubCategory("amateursportsteams");
		active.addSubCategory("amusementparks");
		active.addSubCategory("aquariums");
		active.addSubCategory("archery");
		active.addSubCategory("beaches");
		active.addSubCategory("bikerentals");
		active.addSubCategory("boating");
		active.addSubCategory("bowling");
		active.addSubCategory("climbing");
		active.addSubCategory("discgolf");
		active.addSubCategory("diving");
		
		CategoryTree diving = active.addSubCategory("diving");
		diving.addSubCategory("freediving");
		diving.addSubCategory("scuba");

		active.addSubCategory("fishing");
		
		CategoryTree fitness = active.addSubCategory("fitness");
		fitness.addSubCategory("dancestudio");
		fitness.addSubCategory("gyms");
		fitness.addSubCategory("martialarts");
		fitness.addSubCategory("pilates");
		fitness.addSubCategory("swimminglessons");
		fitness.addSubCategory("taichi");
		fitness.addSubCategory("healthtrainers");
		fitness.addSubCategory("yoga");

		active.addSubCategory("gokarts");
		active.addSubCategory("golf");
		active.addSubCategory("gun_ranges");
		active.addSubCategory("hiking");
		active.addSubCategory("horseracing");
		active.addSubCategory("horsebackriding");
		active.addSubCategory("lakes");
		active.addSubCategory("leisure_centers");
		active.addSubCategory("mini_golf");
		active.addSubCategory("mountainbiking");

		CategoryTree parks = active.addSubCategory("parks");
		parks.addSubCategory("dog_parks");
		parks.addSubCategory("skate_parks");

		active.addSubCategory("playgrounds");
		active.addSubCategory("rafting");
		active.addSubCategory("recreation");
		active.addSubCategory("skatingrinks");
		active.addSubCategory("skydiving");
		active.addSubCategory("football");
		active.addSubCategory("sports_clubs");
		active.addSubCategory("summer_camps");
		active.addSubCategory("swimmingpools");
		active.addSubCategory("tennis");
		active.addSubCategory("zoos");
		
//ARTS		
		CategoryTree arts = yelpCategories.addSubCategory("arts");
		arts.addSubCategory("arcades");
		arts.addSubCategory("galleries");
		arts.addSubCategory("gardens");
		arts.addSubCategory("casinos");
		arts.addSubCategory("movietheaters");
		arts.addSubCategory("festivals");
		arts.addSubCategory("jazzandblues");
		arts.addSubCategory("museums");
		arts.addSubCategory("musicvenues");
		arts.addSubCategory("opera");
		arts.addSubCategory("theater");
		arts.addSubCategory("sportsteams");
		arts.addSubCategory("psychic_astrology");
		arts.addSubCategory("social_clubs");
		arts.addSubCategory("stadiumsarenas");
		arts.addSubCategory("wineries");
//AUTO		
		CategoryTree auto = yelpCategories.addSubCategory("auto");
		auto.addSubCategory("auto_detailing");
		auto.addSubCategory("autoglass");
		auto.addSubCategory("autopartssupplies");
		auto.addSubCategory("autorepair");
		auto.addSubCategory("bodyshops");
		auto.addSubCategory("car_dealers");
		auto.addSubCategory("stereo_installation");
		auto.addSubCategory("carwash");
		auto.addSubCategory("servicestations");
		auto.addSubCategory("motorcycledealers");
		auto.addSubCategory("motorcyclerepair");
		auto.addSubCategory("oilchange");
		auto.addSubCategory("parking");
		auto.addSubCategory("smog_check_stations");
		auto.addSubCategory("tires");
		auto.addSubCategory("towing");
		auto.addSubCategory("windshieldinstallrepair");
		auto.addSubCategory("");
		auto.addSubCategory("");
		auto.addSubCategory("");
//BEAUTY
		CategoryTree beautysvc = yelpCategories.addSubCategory("beautysvc");
		beautysvc.addSubCategory("barbers");
		beautysvc.addSubCategory("cosmetics");
		beautysvc.addSubCategory("spas");
		beautysvc.addSubCategory("eyelashservice");
		
		CategoryTree hairremoval = beautysvc.addSubCategory("hairremoval");
		hairremoval.addSubCategory("laser_hair_removal");
		
		beautysvc.addSubCategory("hair");
		beautysvc.addSubCategory("makeupartists");
		beautysvc.addSubCategory("massage");
		beautysvc.addSubCategory("medicalspa");
		beautysvc.addSubCategory("othersalons");
		beautysvc.addSubCategory("piercing");
		beautysvc.addSubCategory("skincare");
		beautysvc.addSubCategory("tanning");
		beautysvc.addSubCategory("tattoo");

//EDUCATION
		CategoryTree education = yelpCategories.addSubCategory("education");
		education.addSubCategory("adultedu");
		education.addSubCategory("collegeuniv");
		education.addSubCategory("educationservices");
		education.addSubCategory("elementaryschools");
		education.addSubCategory("highschools");
		education.addSubCategory("preschools");
		education.addSubCategory("privatetutors");
		education.addSubCategory("specialed");
		
		CategoryTree specialtyschools = education.addSubCategory("specialtyschools");
		specialtyschools.addSubCategory("artschools");
		specialtyschools.addSubCategory("cookingschools");
		specialtyschools.addSubCategory("cosmetology_schools");
		specialtyschools.addSubCategory("dance_schools");
		specialtyschools.addSubCategory("driving_schools");
		specialtyschools.addSubCategory("flightinstruction");
		specialtyschools.addSubCategory("language_schools");
		specialtyschools.addSubCategory("massage_schools");
		specialtyschools.addSubCategory("swimminglessons");
		specialtyschools.addSubCategory("vocation");
		
		education.addSubCategory("testprep");
		education.addSubCategory("tutoring");
		
//Event Planning & Services
		CategoryTree eventservices = yelpCategories.addSubCategory("eventservices");
		eventservices.addSubCategory("boatcharters");
		eventservices.addSubCategory("stationery");
		eventservices.addSubCategory("catering");
		eventservices.addSubCategory("djs");
		eventservices.addSubCategory("hotels");
		eventservices.addSubCategory("eventplanning");
		eventservices.addSubCategory("partysupplies");
		eventservices.addSubCategory("personalchefs");
		eventservices.addSubCategory("photographers");
		eventservices.addSubCategory("venues");
		eventservices.addSubCategory("videographers");
		eventservices.addSubCategory("wedding_planning");

//Financial Services (financialservices)
		CategoryTree financialservices = yelpCategories.addSubCategory("financialservices");
		financialservices.addSubCategory("banks");
		financialservices.addSubCategory("paydayloans");
		financialservices.addSubCategory("financialadvising");
		financialservices.addSubCategory("insurance");
		financialservices.addSubCategory("investing");
		financialservices.addSubCategory("taxservices");

//		Food (food)
		CategoryTree food = yelpCategories.addSubCategory("food");
		food.addSubCategory("bagels");
		food.addSubCategory("bakeries");
		food.addSubCategory("beer_and_wine");
		food.addSubCategory("breweries");
		food.addSubCategory("butcher");
		food.addSubCategory("coffee");
		food.addSubCategory("convenience");
		food.addSubCategory("desserts");
		food.addSubCategory("diyfood");
		food.addSubCategory("donuts");
		food.addSubCategory("farmersmarket");
		food.addSubCategory("fooddeliveryservices");
		food.addSubCategory("grocery");
		food.addSubCategory("icecream");
		food.addSubCategory("internetcafe");
		food.addSubCategory("juicebars");
		
		CategoryTree gourmet = food.addSubCategory("gourmet");
		gourmet.addSubCategory("candy");
		gourmet.addSubCategory("cheese");
		gourmet.addSubCategory("chocolate");
		gourmet.addSubCategory("ethnicmarkets");
		gourmet.addSubCategory("markets");
		gourmet.addSubCategory("healthmarkets");
		gourmet.addSubCategory("herbsandspices");
		gourmet.addSubCategory("meats");
		gourmet.addSubCategory("seafoodmarkets");

		food.addSubCategory("streetvendors");
		food.addSubCategory("tea");
		food.addSubCategory("wineries");

//Health and Medical (health)
		CategoryTree health = yelpCategories.addSubCategory("health");
		health.addSubCategory("acupuncture");
		health.addSubCategory("cannabis_clinics");
		health.addSubCategory("chiropractors");
		health.addSubCategory("c_and_mh");

		CategoryTree dentists = health.addSubCategory("dentists");
		dentists.addSubCategory("cosmeticdentists");
		dentists.addSubCategory("endodontists");
		dentists.addSubCategory("generaldentistry");
		dentists.addSubCategory("oralsurgeons");
		dentists.addSubCategory("orthodontists");
		dentists.addSubCategory("pediatric_dentists");
		dentists.addSubCategory("periodontists");
		
		CategoryTree physicians = health.addSubCategory("physicians");
		physicians.addSubCategory("allergist");
		physicians.addSubCategory("audiologist");
		physicians.addSubCategory("cardiology");
		physicians.addSubCategory("cosmeticsurgeons");
		physicians.addSubCategory("dermatology");
		physicians.addSubCategory("earnosethroat");
		physicians.addSubCategory("familydr");
		physicians.addSubCategory("fertility");
		physicians.addSubCategory("gastroenterologist");
		physicians.addSubCategory("gerontologist");
		physicians.addSubCategory("internalmed");
		physicians.addSubCategory("naturopathic");
		physicians.addSubCategory("neurologist");
		physicians.addSubCategory("obgyn");
		physicians.addSubCategory("oncologist");
		physicians.addSubCategory("opthamalogists");
		physicians.addSubCategory("orthopedists");
		physicians.addSubCategory("osteopathicphysicians");
		physicians.addSubCategory("pediatricians");
		physicians.addSubCategory("podiatrists");
		physicians.addSubCategory("proctologist");
		physicians.addSubCategory("psychiatrists");
		physicians.addSubCategory("pulmonologist");
		physicians.addSubCategory("sportsmed");
		physicians.addSubCategory("tattooremoval");
		
		health.addSubCategory("homehealthcare");
		health.addSubCategory("hospice");
		health.addSubCategory("hospitals");
		health.addSubCategory("laserlasikeyes");
		health.addSubCategory("medcenters");
		health.addSubCategory("medicalspa");
		health.addSubCategory("midwives");
		health.addSubCategory("nutritionists");
		health.addSubCategory("optometrists");
		health.addSubCategory("physicaltherapy");
		health.addSubCategory("retirement_homes");
		health.addSubCategory("speech_therapists");
		health.addSubCategory("tcm");
		health.addSubCategory("urgent_care");
		health.addSubCategory("weightlosscenters");
		
//		Home Services (homeservices)
		CategoryTree homeservices = yelpCategories.addSubCategory("homeservices");
		homeservices.addSubCategory("buildingsupplies");
		homeservices.addSubCategory("carpetinstallation");
		homeservices.addSubCategory("carpeting");
		homeservices.addSubCategory("contractors");
		homeservices.addSubCategory("electricians");
		homeservices.addSubCategory("flooring");
		homeservices.addSubCategory("gardeners");
		homeservices.addSubCategory("handyman");
		homeservices.addSubCategory("hvac");
		homeservices.addSubCategory("homecleaning");
		homeservices.addSubCategory("home_inspectors");
		homeservices.addSubCategory("hometheatreinstallation");
		homeservices.addSubCategory("interiordesign");
		homeservices.addSubCategory("isps");
		homeservices.addSubCategory("locksmiths");
		homeservices.addSubCategory("landscapearchitects");
		homeservices.addSubCategory("landscaping");
		homeservices.addSubCategory("lighting");
		homeservices.addSubCategory("movers");
		homeservices.addSubCategory("painters");
		homeservices.addSubCategory("plumbing");
		homeservices.addSubCategory("poolcleaners");

		CategoryTree home_realestate = homeservices.addSubCategory("realestate");
		home_realestate.addSubCategory("apartments");
		home_realestate.addSubCategory("homestaging");
		home_realestate.addSubCategory("mortgagebrokers");
		home_realestate.addSubCategory("propertymgmt");
		home_realestate.addSubCategory("realestateagents");
		home_realestate.addSubCategory("realestatesvcs");
		home_realestate.addSubCategory("university_housing");
		
		homeservices.addSubCategory("roofing");
		homeservices.addSubCategory("securitysystems");
		homeservices.addSubCategory("blinds");
		homeservices.addSubCategory("solarinstallation");
		homeservices.addSubCategory("televisionserviceproviders");
		homeservices.addSubCategory("treeservices");
		homeservices.addSubCategory("windowwashing");
		homeservices.addSubCategory("windowsinstallation");
		

//Hotels & Travel (hotelstravel)
		CategoryTree hotelstravel = yelpCategories.addSubCategory("hotelstravel");
		hotelstravel.addSubCategory("airports");
		hotelstravel.addSubCategory("bedbreakfast");
		hotelstravel.addSubCategory("campgrounds");
		hotelstravel.addSubCategory("carrental");
		hotelstravel.addSubCategory("guesthouses");
		hotelstravel.addSubCategory("hostels");
		hotelstravel.addSubCategory("hotels");
		hotelstravel.addSubCategory("rvrental");
		hotelstravel.addSubCategory("skiresorts");
		hotelstravel.addSubCategory("tours");

		CategoryTree transport = hotelstravel.addSubCategory("transport");
		transport.addSubCategory("airlines");
		transport.addSubCategory("airport_shuttles");
		transport.addSubCategory("limos");
		transport.addSubCategory("publictransport");
		transport.addSubCategory("taxis");
		
		hotelstravel.addSubCategory("travelservices");
		hotelstravel.addSubCategory("vacationrentalagents");

//Local Flavor (localflavor)
		yelpCategories.addSubCategory("localflavor");

//Local Services (localservices)
		CategoryTree localservices = yelpCategories.addSubCategory("localservices");
		localservices.addSubCategory("homeappliancerepair");
		localservices.addSubCategory("bailbondsmen");
		localservices.addSubCategory("carpet_cleaning");
		localservices.addSubCategory("childcare");
		localservices.addSubCategory("nonprofit");
		localservices.addSubCategory("couriers");
		localservices.addSubCategory("drycleaninglaundry");
		localservices.addSubCategory("electronicsrepair");
		localservices.addSubCategory("funeralservices");
		localservices.addSubCategory("reupholstery");
		localservices.addSubCategory("itservices");
		localservices.addSubCategory("junkremovalandhauling");
		localservices.addSubCategory("notaries");
		localservices.addSubCategory("pest_control");
		localservices.addSubCategory("copyshops");
		localservices.addSubCategory("recording_studios");
		localservices.addSubCategory("recyclingcenter");
		localservices.addSubCategory("screenprinting");
		localservices.addSubCategory("selfstorage");
		localservices.addSubCategory("sewingalterations");
		localservices.addSubCategory("shipping_centers");
		localservices.addSubCategory("shoerepair");
		localservices.addSubCategory("snowremoval");
		localservices.addSubCategory("watch_repair");

//Mass Media (massmedia)
		CategoryTree massmedia = yelpCategories.addSubCategory("massmedia");
		massmedia.addSubCategory("printmedia");
		massmedia.addSubCategory("radiostations");
		massmedia.addSubCategory("televisionstations");

//Nightlife (nightlife)
		CategoryTree nightlife = yelpCategories.addSubCategory("nightlife");
		nightlife.addSubCategory("adultentertainment");

		CategoryTree bars = nightlife.addSubCategory("bars");
		bars.addSubCategory("champagne_bars");
		bars.addSubCategory("divebars");
		bars.addSubCategory("gaybars");
		bars.addSubCategory("hookah_bars");
		bars.addSubCategory("lounges");
		bars.addSubCategory("pubs");
		bars.addSubCategory("sportsbars");
		bars.addSubCategory("wine_bars");
		
		nightlife.addSubCategory("comedyclubs");
		nightlife.addSubCategory("danceclubs");
		nightlife.addSubCategory("jazzandblues");
		nightlife.addSubCategory("karaoke");
		nightlife.addSubCategory("musicvenues");
		nightlife.addSubCategory("poolhalls");

//Pets (pets)
		CategoryTree pets = yelpCategories.addSubCategory("pets");
		pets.addSubCategory("animalshelters");

		CategoryTree petservices = pets.addSubCategory("petservices");
		petservices.addSubCategory("dogwalkers");
		petservices.addSubCategory("pet_sitting");
		petservices.addSubCategory("groomer");
		petservices.addSubCategory("pet_training");
		
		pets.addSubCategory("petstore");
		pets.addSubCategory("vet");

//Professional Services (professional)
		CategoryTree professional = yelpCategories.addSubCategory("professional");
		professional.addSubCategory("accountants");
		professional.addSubCategory("advertising");
		professional.addSubCategory("architects");
		professional.addSubCategory("boatrepair");
		professional.addSubCategory("careercounseling");
		professional.addSubCategory("employmentagencies");
		professional.addSubCategory("graphicdesign");
		professional.addSubCategory("isps");

		CategoryTree lawyers = professional.addSubCategory("lawyers");
		lawyers.addSubCategory("bankruptcy");
		lawyers.addSubCategory("criminaldefense");
		lawyers.addSubCategory("divorce");
		lawyers.addSubCategory("employmentlawyers");
		lawyers.addSubCategory("estateplanning");
		lawyers.addSubCategory("general_litigation");
		lawyers.addSubCategory("immigrationlawyers");
		lawyers.addSubCategory("personal_injury");
		lawyers.addSubCategory("realestatelawyers");
		
		professional.addSubCategory("lifecoach");
		professional.addSubCategory("marketing");
		professional.addSubCategory("officecleaning");
		professional.addSubCategory("privateinvestigation");
		professional.addSubCategory("publicrelations");
		professional.addSubCategory("videofilmproductions");
		professional.addSubCategory("web_design");

//Public Services & Government (publicservicesgovt)
		CategoryTree publicservicesgovt = yelpCategories.addSubCategory("publicservicesgovt");
		publicservicesgovt.addSubCategory("departmentsofmotorvehicles");
		publicservicesgovt.addSubCategory("landmarks");
		publicservicesgovt.addSubCategory("libraries");
		publicservicesgovt.addSubCategory("policedepartments");
		publicservicesgovt.addSubCategory("postoffices");

//Real Estate (realestate)
		CategoryTree realestate = yelpCategories.addSubCategory("realestate");
		realestate.addSubCategory("apartments");
		realestate.addSubCategory("homestaging");
		realestate.addSubCategory("mortgagebrokers");
		realestate.addSubCategory("propertymgmt");
		realestate.addSubCategory("realestateagents");
		realestate.addSubCategory("realestatesvcs");
		realestate.addSubCategory("university_housing");

//Religious Organizations (religiousorgs)
		CategoryTree religiousorgs = yelpCategories.addSubCategory("religiousorgs");
		religiousorgs.addSubCategory("buddhist_temples");
		religiousorgs.addSubCategory("churches");
		religiousorgs.addSubCategory("hindu_temples");
		religiousorgs.addSubCategory("mosques");
		religiousorgs.addSubCategory("synagogues");

//Restaurants (restaurants)
		CategoryTree restaurants = yelpCategories.addSubCategory("restaurants");
		restaurants.addSubCategory("afghani");
		restaurants.addSubCategory("african");
		restaurants.addSubCategory("newamerican");
		restaurants.addSubCategory("tradamerican");
		restaurants.addSubCategory("argentine");
		restaurants.addSubCategory("asianfusion");
		restaurants.addSubCategory("bbq");
		restaurants.addSubCategory("basque");
		restaurants.addSubCategory("belgian");
		restaurants.addSubCategory("brasseries");
		restaurants.addSubCategory("brazilian");
		restaurants.addSubCategory("breakfast_brunch");
		restaurants.addSubCategory("british");
		restaurants.addSubCategory("buffets");
		restaurants.addSubCategory("burgers");
		restaurants.addSubCategory("burmese");
		restaurants.addSubCategory("cafes");
		restaurants.addSubCategory("cajun");
		restaurants.addSubCategory("cambodian");
		restaurants.addSubCategory("caribbean");
		restaurants.addSubCategory("cheesesteaks");
		restaurants.addSubCategory("chicken_wings");

		CategoryTree chinese = restaurants.addSubCategory("chinese");
		chinese.addSubCategory("dimsum");
		
		restaurants.addSubCategory("creperies");
		restaurants.addSubCategory("cuban");
		restaurants.addSubCategory("delis");
		restaurants.addSubCategory("diners");
		restaurants.addSubCategory("ethiopian");
		restaurants.addSubCategory("hotdogs");
		restaurants.addSubCategory("filipino");
		restaurants.addSubCategory("fishnchips");
		restaurants.addSubCategory("fondue");
		restaurants.addSubCategory("foodstands");
		restaurants.addSubCategory("french");
		restaurants.addSubCategory("gastropubs");
		restaurants.addSubCategory("german");
		restaurants.addSubCategory("gluten_free");
		restaurants.addSubCategory("greek");
		restaurants.addSubCategory("halal");
		restaurants.addSubCategory("hawaiian");
		restaurants.addSubCategory("himalayan");
		restaurants.addSubCategory("hotdog");
		restaurants.addSubCategory("hungarian");
		restaurants.addSubCategory("indpak");
		restaurants.addSubCategory("indonesian");
		restaurants.addSubCategory("irish");
		restaurants.addSubCategory("italian");
		restaurants.addSubCategory("japanese");
		restaurants.addSubCategory("korean");
		restaurants.addSubCategory("kosher");
		restaurants.addSubCategory("latin");
		restaurants.addSubCategory("raw_food");
		restaurants.addSubCategory("malaysian");
		restaurants.addSubCategory("mediterranean");
		restaurants.addSubCategory("mexican");
		restaurants.addSubCategory("mideastern");
		restaurants.addSubCategory("modern_european");
		restaurants.addSubCategory("mongolian");
		restaurants.addSubCategory("moroccan");
		restaurants.addSubCategory("pakistani");
		restaurants.addSubCategory("persian");
		restaurants.addSubCategory("peruvian");
		restaurants.addSubCategory("pizza");
		restaurants.addSubCategory("polish");
		restaurants.addSubCategory("portuguese");
		restaurants.addSubCategory("russian");
		restaurants.addSubCategory("sandwiches");
		restaurants.addSubCategory("scandinavian");
		restaurants.addSubCategory("seafood");
		restaurants.addSubCategory("singaporean");
		restaurants.addSubCategory("soulfood");
		restaurants.addSubCategory("soup");
		restaurants.addSubCategory("southern");
		restaurants.addSubCategory("spanish");
		restaurants.addSubCategory("steak");
		restaurants.addSubCategory("sushi");
		restaurants.addSubCategory("taiwanese");
		restaurants.addSubCategory("tapas");
		restaurants.addSubCategory("tapasmallplates");
		restaurants.addSubCategory("tex-mex");
		restaurants.addSubCategory("thai");
		restaurants.addSubCategory("turkish");
		restaurants.addSubCategory("ukrainian");
		restaurants.addSubCategory("vegan");
		restaurants.addSubCategory("vegetarian");
		restaurants.addSubCategory("vietnamese");

//Shopping (shopping)
		CategoryTree shopping = yelpCategories.addSubCategory("shopping");
		shopping.addSubCategory("adult");
		shopping.addSubCategory("antiques");
		shopping.addSubCategory("galleries");

		CategoryTree artsandcrafts = shopping.addSubCategory("artsandcrafts");
		artsandcrafts.addSubCategory("artsupplies");
		artsandcrafts.addSubCategory("stationery");
		artsandcrafts.addSubCategory("costumes");
		artsandcrafts.addSubCategory("fabricstores");
		artsandcrafts.addSubCategory("framing");
				
		shopping.addSubCategory("baby_gear");

		CategoryTree media = shopping.addSubCategory("media");
		media.addSubCategory("bookstores");
		media.addSubCategory("comicbooks");
		media.addSubCategory("musicvideo");
		media.addSubCategory("mags");
		media.addSubCategory("videoandgames");
		media.addSubCategory("vinyl_records");
		
		shopping.addSubCategory("bridal");
		shopping.addSubCategory("computers");
		shopping.addSubCategory("cosmetics");
		shopping.addSubCategory("deptstores");
		shopping.addSubCategory("discountstore");
		shopping.addSubCategory("drugstores");
		shopping.addSubCategory("electronics");
		shopping.addSubCategory("opticians");

		CategoryTree fashion = shopping.addSubCategory("fashion");
		fashion.addSubCategory("accessories");
		fashion.addSubCategory("childcloth");
		fashion.addSubCategory("deptstores");
		fashion.addSubCategory("leather");
		fashion.addSubCategory("lingerie");
		fashion.addSubCategory("maternity");
		fashion.addSubCategory("menscloth");
		fashion.addSubCategory("shoes");
		fashion.addSubCategory("sportswear");
		fashion.addSubCategory("swimwear");
		fashion.addSubCategory("vintage");
		fashion.addSubCategory("womenscloth");
		
		CategoryTree flowers = shopping.addSubCategory("flowers");
		flowers.addSubCategory("stationery");
		flowers.addSubCategory("florists");
		
		shopping.addSubCategory("hobbyshops");

		CategoryTree homeandgarden = shopping.addSubCategory("homeandgarden");
		homeandgarden.addSubCategory("appliances");
		homeandgarden.addSubCategory("furniture");
		homeandgarden.addSubCategory("hardware");
		homeandgarden.addSubCategory("homedecor");
		homeandgarden.addSubCategory("hottubandpool");
		homeandgarden.addSubCategory("kitchenandbath");
		homeandgarden.addSubCategory("mattresses");
		homeandgarden.addSubCategory("gardening");
		
		shopping.addSubCategory("jewelry");
		shopping.addSubCategory("knittingsupplies");
		shopping.addSubCategory("luggage");
		shopping.addSubCategory("mobilephones");
		shopping.addSubCategory("musicalinstrumentsandteachers");
		shopping.addSubCategory("officeequipment");
		shopping.addSubCategory("outlet_stores");
		shopping.addSubCategory("pawn");
		shopping.addSubCategory("personal_shopping");
		shopping.addSubCategory("photographystores");
		shopping.addSubCategory("shoppingcenters");

		CategoryTree sportgoods = shopping.addSubCategory("sportgoods");
		sportgoods.addSubCategory("bikes");
		sportgoods.addSubCategory("outdoorgear");
		sportgoods.addSubCategory("sportswear");
		
		shopping.addSubCategory("thrift_stores");
		shopping.addSubCategory("tobaccoshops");
		shopping.addSubCategory("toys");
		shopping.addSubCategory("watches");
		shopping.addSubCategory("wholesale_stores");

	}		
}
