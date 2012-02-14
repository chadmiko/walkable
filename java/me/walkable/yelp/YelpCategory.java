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
	}
		

}


//	Event Planning & Services (eventservices)
//	Boat Charters (boatcharters)
//	Cards & Stationery (stationery)
//	Caterers (catering)
//	DJs (djs)
//	Hotels (hotels)
//	Party & Event Planning (eventplanning)
//	Party Supplies (partysupplies)
//	Personal Chefs (personalchefs)
//	Photographers (photographers)
//	Venues & Event Spaces (venues)
//	Videographers (videographers)
//	Wedding Planning (wedding_planning)
//	Financial Services (financialservices)
//	Banks & Credit Unions (banks)
//	Check Cashing/Pay-day Loans (paydayloans)
//	Financial Advising (financialadvising)
//	Insurance (insurance)
//	Investing (investing)
//	Tax Services (taxservices)
//	Food (food)
//	Bagels (bagels)
//	Bakeries (bakeries)
//	Beer, Wine & Spirits (beer_and_wine)
//	Breweries (breweries)
//	Butcher (butcher)
//	Coffee & Tea (coffee)
//	Convenience Stores (convenience)
//	Desserts (desserts)
//	Do-It-Yourself Food (diyfood)
//	Donuts (donuts)
//	Farmers Market (farmersmarket)
//	Food Delivery Services (fooddeliveryservices)
//	Grocery (grocery)
//	Ice Cream & Frozen Yogurt (icecream)
//	Internet Cafes (internetcafe)
//	Juice Bars & Smoothies (juicebars)
//	Specialty Food (gourmet)
//	Candy Stores (candy)
//	Cheese Shops (cheese)
//	Chocolatiers and Shops (chocolate)
//	Ethnic Food (ethnicmarkets)
//	Fruits & Veggies (markets)
//	Health Markets (healthmarkets)
//	Herbs and Spices (herbsandspices)
//	Meat Shops (meats)
//	Seafood Markets (seafoodmarkets)
//	Street Vendors (streetvendors)
//	Tea Rooms (tea)
//	Wineries (wineries)
//	Health and Medical (health)
//	Acupuncture (acupuncture)
//	Cannabis Clinics (cannabis_clinics)
//	Chiropractors (chiropractors)
//	Counseling & Mental Health (c_and_mh)
//	Dentists (dentists)
//	Cosmetic Dentists (cosmeticdentists)
//	Endodontists (endodontists)
//	General Dentistry (generaldentistry)
//	Oral Surgeons (oralsurgeons)
//	Orthodontists (orthodontists)
//	Pediatric Dentists (pediatric_dentists)
//	Periodontists (periodontists)
//	Doctors (physicians)
//	Allergists (allergist)
//	Audiologist (audiologist)
//	Cardiologists (cardiology)
//	Cosmetic Surgeons (cosmeticsurgeons)
//	Dermatologists (dermatology)
//	Ear Nose & Throat (earnosethroat)
//	Family Practice (familydr)
//	Fertility (fertility)
//	Gastroenterologist (gastroenterologist)
//	Gerontologists (gerontologist)
//	Internal Medicine (internalmed)
//	Naturopathic/Holistic (naturopathic)
//	Neurologist (neurologist)
//	Obstetricians and Gynecologists (obgyn)
//	Oncologist (oncologist)
//	Ophthalmologists (opthamalogists)
//	Orthopedists (orthopedists)
//	Osteopathic Physicians (osteopathicphysicians)
//	Pediatricians (pediatricians)
//	Podiatrists (podiatrists)
//	Proctologists (proctologist)
//	Psychiatrists (psychiatrists)
//	Pulmonologist (pulmonologist)
//	Sports Medicine (sportsmed)
//	Tattoo Removal (tattooremoval)
//	Home Health Care (homehealthcare)
//	Hospice (hospice)
//	Hospitals (hospitals)
//	Laser Eye Surgery/Lasik (laserlasikeyes)
//	Medical Centers (medcenters)
//	Medical Spas (medicalspa)
//	Midwives (midwives)
//	Nutritionists (nutritionists)
//	Optometrists (optometrists)
//	Physical Therapy (physicaltherapy)
//	Retirement Homes (retirement_homes)
//	Speech Therapists (speech_therapists)
//	Traditional Chinese Medicine (tcm)
//	Urgent Care (urgent_care)
//	Weight Loss Centers (weightlosscenters)
//	Home Services (homeservices)
//	Building Supplies (buildingsupplies)
//	Carpet Installation (carpetinstallation)
//	Carpeting (carpeting)
//	Contractors (contractors)
//	Electricians (electricians)
//	Flooring (flooring)
//	Gardeners (gardeners)
//	Handyman (handyman)
//	Heating & Air Conditioning/HVAC (hvac)
//	Home Cleaning (homecleaning)
//	Home Inspectors (home_inspectors)
//	Home Theatre Installation (hometheatreinstallation)
//	Interior Design (interiordesign)
//	Internet Service Providers (isps)
//	Keys & Locksmiths (locksmiths)
//	Landscape Architects (landscapearchitects)
//	Landscaping (landscaping)
//	Lighting Fixtures & Equipment (lighting)
//	Movers (movers)
//	Painters (painters)
//	Plumbing (plumbing)
//	Pool Cleaners (poolcleaners)
//	Real Estate (realestate)
//	Apartments (apartments)
//	Home Staging (homestaging)
//	Mortgage Brokers (mortgagebrokers)
//	Property Management (propertymgmt)
//	Real Estate Agents (realestateagents)
//	Real Estate Services (realestatesvcs)
//	University Housing (university_housing)
//	Roofing (roofing)
//	Security Systems (securitysystems)
//	Shades & Blinds (blinds)
//	Solar Installation (solarinstallation)
//	Television Service Providers (televisionserviceproviders)
//	Tree Services (treeservices)
//	Window Washing (windowwashing)
//	Windows Installation (windowsinstallation)
//	Hotels & Travel (hotelstravel)
//	Airports (airports)
//	Bed & Breakfast (bedbreakfast)
//	Campgrounds (campgrounds)
//	Car Rental (carrental)
//	Guest Houses (guesthouses)
//	Hostels (hostels)
//	Hotels (hotels)
//	RV Rental (rvrental)
//	Ski Resorts (skiresorts)
//	Tours (tours)
//	Transportation (transport)
//	Airlines (airlines)
//	Airport Shuttles (airport_shuttles)
//	Limos (limos)
//	Public Transportation (publictransport)
//	Taxis (taxis)
//	Travel Services (travelservices)
//	Vacation Rental Agents (vacationrentalagents)
//	Local Flavor (localflavor)
//	Local Services (localservices)
//	Appliances & Repair (homeappliancerepair)
//	Bail Bondsmen (bailbondsmen)
//	Carpet Cleaning (carpet_cleaning)
//	Child Care & Day Care (childcare)
//	Community Service/Non-Profit (nonprofit)
//	Couriers & Delivery Services (couriers)
//	Dry Cleaning & Laundry (drycleaninglaundry)
//	Electronics Repair (electronicsrepair)
//	Funeral Services & Cemeteries (funeralservices)
//	Furniture Reupholstery (reupholstery)
//	IT Services & Computer Repair (itservices)
//	Junk Removal and Hauling (junkremovalandhauling)
//	Notaries (notaries)
//	Pest Control (pest_control)
//	Printing Services (copyshops)
//	Recording & Rehearsal Studios (recording_studios)
//	Recycling Center (recyclingcenter)
//	Screen Printing (screenprinting)
//	Self Storage (selfstorage)
//	Sewing & Alterations (sewingalterations)
//	Shipping Centers (shipping_centers)
//	Shoe Repair (shoerepair)
//	Snow Removal (snowremoval)
//	Watch Repair (watch_repair)
//	Mass Media (massmedia)
//	Print Media (printmedia)
//	Radio Stations (radiostations)
//	Television Stations (televisionstations)
//	Nightlife (nightlife)
//	Adult Entertainment (adultentertainment)
//	Bars (bars)
//	Champagne Bars (champagne_bars)
//	Dive Bars (divebars)
//	Gay Bars (gaybars)
//	Hookah Bars (hookah_bars)
//	Lounges (lounges)
//	Pubs (pubs)
//	Sports Bars (sportsbars)
//	Wine Bars (wine_bars)
//	Comedy Clubs (comedyclubs)
//	Dance Clubs (danceclubs)
//	Jazz & Blues (jazzandblues)
//	Karaoke (karaoke)
//	Music Venues (musicvenues)
//	Pool Halls (poolhalls)
//	Pets (pets)
//	Animal Shelters (animalshelters)
//	Pet Services (petservices)
//	Dog Walkers (dogwalkers)
//	Pet Boarding/Pet Sitting (pet_sitting)
//	Pet Groomers (groomer)
//	Pet Training (pet_training)
//	Pet Stores (petstore)
//	Veterinarians (vet)
//	Professional Services (professional)
//	Accountants (accountants)
//	Advertising (advertising)
//	Architects (architects)
//	Boat Repair (boatrepair)
//	Career Counseling (careercounseling)
//	Employment Agencies (employmentagencies)
//	Graphic Design (graphicdesign)
//	Internet Service Providers (isps)
//	Lawyers (lawyers)
//	Bankruptcy (bankruptcy)
//	Criminal Defense (criminaldefense)
//	Divorce and Family Law (divorce)
//	Employment (employmentlawyers)
//	Estate Planning (estateplanning)
//	General Litigation (general_litigation)
//	Immigration (immigrationlawyers)
//	Personal Injury (personal_injury)
//	Real Estate Lawyers (realestatelawyers)
//	Life Coach (lifecoach)
//	Marketing (marketing)
//	Office Cleaning (officecleaning)
//	Private Investigation (privateinvestigation)
//	Public Relations (publicrelations)
//	Video/Film Production (videofilmproductions)
//	Web Design (web_design)
//	Public Services & Government (publicservicesgovt)
//	Departments of Motor Vehicles (departmentsofmotorvehicles)
//	Landmarks & Historical Buildings (landmarks)
//	Libraries (libraries)
//	Police Departments (policedepartments)
//	Post Offices (postoffices)
//	Real Estate (realestate)
//	Apartments (apartments)
//	Home Staging (homestaging)
//	Mortgage Brokers (mortgagebrokers)
//	Property Management (propertymgmt)
//	Real Estate Agents (realestateagents)
//	Real Estate Services (realestatesvcs)
//	University Housing (university_housing)
//	Religious Organizations (religiousorgs)
//	Buddhist Temples (buddhist_temples)
//	Churches (churches)
//	Hindu Temples (hindu_temples)
//	Mosques (mosques)
//	Synagogues (synagogues)
//	Restaurants (restaurants)
//	Afghan (afghani)
//	African (african)
//	American (New) (newamerican)
//	American (Traditional) (tradamerican)
//	Argentine (argentine)
//	Asian Fusion (asianfusion)
//	Barbeque (bbq)
//	Basque (basque)
//	Belgian (belgian)
//	Brasseries (brasseries)
//	Brazilian (brazilian)
//	Breakfast & Brunch (breakfast_brunch)
//	British (british)
//	Buffets (buffets)
//	Burgers (burgers)
//	Burmese (burmese)
//	Cafes (cafes)
//	Cajun/Creole (cajun)
//	Cambodian (cambodian)
//	Caribbean (caribbean)
//	Cheesesteaks (cheesesteaks)
//	Chicken Wings (chicken_wings)
//	Chinese (chinese)
//	Dim Sum (dimsum)
//	Creperies (creperies)
//	Cuban (cuban)
//	Delis (delis)
//	Diners (diners)
//	Ethiopian (ethiopian)
//	Fast Food (hotdogs)
//	Filipino (filipino)
//	Fish & Chips (fishnchips)
//	Fondue (fondue)
//	Food Stands (foodstands)
//	French (french)
//	Gastropubs (gastropubs)
//	German (german)
//	Gluten-Free (gluten_free)
//	Greek (greek)
//	Halal (halal)
//	Hawaiian (hawaiian)
//	Himalayan/Nepalese (himalayan)
//	Hot Dogs (hotdog)
//	Hungarian (hungarian)
//	Indian (indpak)
//	Indonesian (indonesian)
//	Irish (irish)
//	Italian (italian)
//	Japanese (japanese)
//	Korean (korean)
//	Kosher (kosher)
//	Latin American (latin)
//	Live/Raw Food (raw_food)
//	Malaysian (malaysian)
//	Mediterranean (mediterranean)
//	Mexican (mexican)
//	Middle Eastern (mideastern)
//	Modern European (modern_european)
//	Mongolian (mongolian)
//	Moroccan (moroccan)
//	Pakistani (pakistani)
//	Persian/Iranian (persian)
//	Peruvian (peruvian)
//	Pizza (pizza)
//	Polish (polish)
//	Portuguese (portuguese)
//	Russian (russian)
//	Sandwiches (sandwiches)
//	Scandinavian (scandinavian)
//	Seafood (seafood)
//	Singaporean (singaporean)
//	Soul Food (soulfood)
//	Soup (soup)
//	Southern (southern)
//	Spanish (spanish)
//	Steakhouses (steak)
//	Sushi Bars (sushi)
//	Taiwanese (taiwanese)
//	Tapas Bars (tapas)
//	Tapas/Small Plates (tapasmallplates)
//	Tex-Mex (tex-mex)
//	Thai (thai)
//	Turkish (turkish)
//	Ukrainian (ukrainian)
//	Vegan (vegan)
//	Vegetarian (vegetarian)
//	Vietnamese (vietnamese)
//	Shopping (shopping)
//	Adult (adult)
//	Antiques (antiques)
//	Art Galleries (galleries)
//	Arts & Crafts (artsandcrafts)
//	Art Supplies (artsupplies)
//	Cards & Stationery (stationery)
//	Costumes (costumes)
//	Fabric Stores (fabricstores)
//	Framing (framing)
//	Baby Gear & Furniture (baby_gear)
//	Books, Mags, Music and Video (media)
//	Bookstores (bookstores)
//	Comic Books (comicbooks)
//	Music & DVDs (musicvideo)
//	Newspapers & Magazines (mags)
//	Videos and Video Game Rental (videoandgames)
//	Vinyl Records (vinyl_records)
//	Bridal (bridal)
//	Computers (computers)
//	Cosmetics & Beauty Supply (cosmetics)
//	Department Stores (deptstores)
//	Discount Store (discountstore)
//	Drugstores (drugstores)
//	Electronics (electronics)
//	Eyewear & Opticians (opticians)
//	Fashion (fashion)
//	Accessories (accessories)
//	Children's Clothing (childcloth)
//	Department Stores (deptstores)
//	Leather Goods (leather)
//	Lingerie (lingerie)
//	Maternity Wear (maternity)
//	Men's Clothing (menscloth)
//	Shoe Stores (shoes)
//	Sports Wear (sportswear)
//	Swimwear (swimwear)
//	Used, Vintage & Consignment (vintage)
//	Women's Clothing (womenscloth)
//	Flowers & Gifts (flowers)
//	Cards & Stationery (stationery)
//	Florists (florists)
//	Hobby Shops (hobbyshops)
//	Home & Garden (homeandgarden)
//	Appliances (appliances)
//	Furniture Stores (furniture)
//	Hardware Stores (hardware)
//	Home Decor (homedecor)
//	Hot Tub and Pool (hottubandpool)
//	Kitchen & Bath (kitchenandbath)
//	Mattresses (mattresses)
//	Nurseries & Gardening (gardening)
//	Jewelry (jewelry)
//	Knitting Supplies (knittingsupplies)
//	Luggage (luggage)
//	Mobile Phones (mobilephones)
//	Musical Instruments & Teachers (musicalinstrumentsandteachers)
//	Office Equipment (officeequipment)
//	Outlet Stores (outlet_stores)
//	Pawn Shops (pawn)
//	Personal Shopping (personal_shopping)
//	Photography Stores & Services (photographystores)
//	Shopping Centers (shoppingcenters)
//	Sporting Goods (sportgoods)
//	Bikes (bikes)
//	Outdoor Gear (outdoorgear)
//	Sports Wear (sportswear)
//	Thrift Stores (thrift_stores)
//	Tobacco Shops (tobaccoshops)
//	Toy Stores (toys)
//	Watches (watches)
//	Wholesale Stores (wholesale_stores)
//	
//}
