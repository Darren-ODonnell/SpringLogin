ToDo
----
Ex: indicate problems found.
Cx: New Code additions (new features)
Rx: Refactoring
Ix: Investigations
Ux: Files currently UnUsed -
Ig: Ignored for now
Dx: Database Changes/Updates
SQLx Sql scripts to incorporate into repositories

# Bugs
    E1 Update and Edit do not work with React
    E7: 
    E8: 
    E10: 

# New Code Cx
    C15:
    C16:
    C17:

# Database Changes
    D2: Load sample Data
    D4: Update data with new Field data
    D5: 
    D6: 
    D7: 


# Refactoring Rx
    R1 Refactor Stat classes
        Stats (Statname and count)
        Dates (Season and FixtureDate
        Players (Firstname, lastname)
        refactoring done - original clases and supporting code left in for now!

    R3: 
    R4: 
    R5: 

# Investigations Ix
    I2: How to implement Change Password
    I3: How to implement forgot password
    I4: 
    I5: 
    I6: 
    
# Files Unused Ux
# Ignored for now Ix

# In Progress    
    E1: Update and Edit do not work with React
        The default attribute used for all endpoints was @ModelAttribute - this is suitable for form-data and not JSON data/objects
        Changes to @RequestBody - this now works with add()
        Need to test with other methods in Competition - then replicate to other controllers.

# sql scripts to incorporate
    SQL6:
    /stats_view/countAllStatAllPlayersOneSeason
    //List of Counts per game-> List for season graph
    SELECT stat_name, firstname, lastname, season, count(*) FROM teamstats.stats_view
    WHERE season = "2022"
    GROUP BY stat_name, last_name+first_name
    ORDER BY stat_name, last_name+first_name;

    SQL7:
    /stats_view/countAllStatAllPlayersAllTime
    //Count per season -> list for all time graph
    SELECT stat_name, first_name, last_name,count(*) FROM teamstats.stats_view
    GROUP BY stat_name, last_name+first_name,
    ORDER BY stat_name, last_name+first_name;

    SQL11:
    SQL12:
    SQL13:

# Completed
    E2 Vulnerabilities appear in Spring modules
        
       Upgrading to 2.6 or 2.7 results in 
        
       ┌─────┐
       |  webSecurityConfig
       ↑     ↓
       |  org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration
       └─────┘
        
       Relying upon circular references is discouraged and they are prohibited by default.
       Update your application to remove the dependency cycle between beans.
       As a last resort, it may be possible to break the cycle automatically by setting s to true.

       Spring updated from 2.5.9 to 2.5.14 - Vulnerabilities with spring disappeared

    E3 Mysql vulnerability
       Mysql updated.
    C1 Stat and Teamsheet classes added
    E4 Change LAZY to EAGER in Temsheet and Stat and Fixture classes.
    D1 Make Stat and Temsheet bridging tables and reload database
       Bridging tables added
    D2 Data Loaded    
    E5 Created findByStatNameFixtureDate but only 3/5 fields displayed on postman 
       Getters omitted from class - these are needed by spring to generate the ResponseBody to return to client
    E6 Select distinct does not work after uuid() id created
       uuid id variable removed, time_occurred and fixturedate used as the primary key
       statviewid used to getters added to StatsView for time_occurreda and fixtureDate
    D6 Removed UUID() as id and import data again using jpa taking time_occurred and fixture_date as the primary keys
       complete
    C2: Add code to create findByStatnameSeason
        complete.
    SQL1: StatsByPlayerBySeason
        /stats_view/countStatnameSeason/
        SELECT season, stat_name, count(*) FROM teamstats.stats_view
        where stat_name = "catchfail"
        group by season;
    SQL2: StatByPLayerByFixture
        /stats_view/countAllStatOnePlayersOneGame
        //Count per game
        SELECT first_name, last_name, stat_name, fixture_date, count(*) FROM teamstats.stats_view
        where first_name = "laura"
        AND last_name = "corcoran"
        and fixture_date = "22-03-06"
        GROUP BY stat_name;
    SQL3:
        /stats_view/countAllStatOnePlayersOneSeason
        //Season Graph -> List of Count per game
        Example: List<List<stat_name, fixture_date, count>>
        SELECT first_name, last_name, stat_name, fixture_date, season, count(*) FROM teamstats.stats_view
        where first_name = "Hannah"
        AND last_name = "begley"
        and season = "2022"
        GROUP BY fixture_date, stat_name
        ORDER BY stat_name, fixture_date;
    SQL4: - same as 3
        /stats_view/countAllStatOnePlayersAllTime
        //All Time Graph -> List of count per season
        SELECT first_name, last_name, stat_name, season, count(*) FROM teamstats.stats_view
        where first_name = "laura"
        AND last_name = "corcoran"
        GROUP BY season, stat_name
        ORDER BY stat_name, season;
        All Players, All Stats
    SQL5:
        /stats_view/countAllStatAllPlayersOneGame
        //Count per game
        SELECT stat_name, fixture_date, count(*) FROM teamstats.stats_view
        WHERE fixture_date = "22-03-06"
        GROUP BY stat_name
        ORDER BY stat_name;
    D5: Change stats.half to int
        config already set to tinyint -> spring classes changed to integer from boolean
    C5: add code to check jwt token
        done /checkToken
    E4 "/stats_view/findBySeason"
        "Index 4 out of bounds for length 3",
        Fixed obj[] assignments corrected in constructor
    C3: Query - get count of statname by fixture date - Done
    C4: Query - get count of statname  by season - Done
    E3 "/stats_view/findByPlayerSeason" error
        "Required request parameter 'firstname' for method parameter type String is not present
        firstname s/b first_name
    D5: Standardize all Statname abbrev field values to uppercase - done
    C6: add Register - done
    C7: Add Find user by id - done
    C8: Add delete user by id - done
    C2: Add Repo Srvc and cntrl for Statname - done 
    D3: Add PanelMember Boolean to Player table - done                
    I1: How to implement Register - coding done and implemented /api/auth/register 
    E5: Generic class mapData in StatViewService generates compiler warnings
        Warning:(150, 75) Raw use of parameterized class 'Class'
        Warning:(156, 25) Unchecked cast: 'java.lang.Object' to 'T'
        inspections suppressed.
    C9:  findByStatNameFixtureDate - Change to new o/p file
    C10: findByStatnameSeason - Change to new o/p file
    C11: findByFirstnameLastnameFixtureDate - Change to new o/p file
    C12: findByFirstnameLastnameSeason - Change to new o/p file
    C13: findByFixtureDate - Change to new o/p file
    C14: findBySeason - Change to new o/p file
         Repository changed - 2 added to all duplicate names
         Select fields, count(*) replace with Select *, count(*)
         Service and Controllers updated - testing required
    R2: In Stat_View change find to count in calls controller/services/repos -> postman 
        Both sets of endpoints changed , chart changed to count also.
    SQL8: replicate first set - this time using * instead of individuial fields
    SQL9: replace '*' with individual attribute names.
    SQL10: Stats - change table contents to uppercase(abbrev)
    E6 "/stats_view/findBySeason" - updated
       