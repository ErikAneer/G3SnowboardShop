
package EJB;

import EntityClasses.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Erik
 */
@Stateless
@LocalBean
public class ProductBean {

    @PersistenceContext(unitName = "SnowboardShop-ejbPU")
    private EntityManager em;

    private List<Product> products;

    /**
     * Get the value of products
     *
     * @return the value of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Set the value of products
     *
     * @param products new value of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void persist(Object object) {
        em.persist(object);
    }


    public List<Product> getAllBoards() {
        
        Query query = em.createNamedQuery("Product.getByType");
        query.setParameter("productType", "snowboard");
        List<Product> results = query.getResultList();
        return results;
    }
    
    public List<Product> getAllBoots() {
        
        Query query = em.createNamedQuery("Product.getByType");
        query.setParameter("productType", "boot");
        List<Product> results = query.getResultList();
        return results;
    }
    public List<Product> getAllBindings() {  
        Query query = em.createNamedQuery("Product.getByType");
        query.setParameter("productType", "binding");
        List<Product> results = query.getResultList();
        return results;
    }
    
     public List<Product> getAllProducts() {
        Query query = em.createNamedQuery("Product.getAll");
        List<Product> results = query.getResultList();
        return results;
    }
    
    public void saveProductToDB() {
        persist(new Product("snowboard", "Ride", "Warpig S", 148, "Warpig är tillbaka! Den här brädan är framtagen för att ha roligt i alla förhållanden med en unik shape som är kortare, "
                + "bredare och avsmalnande för att ge en manövrerbar snowboard som kan varva i parken och flyta som en bräda som är dubbelt så stor. Den här brädan har inte bara en ny innovativ shape,"
                + " RIDE har även inkluderat sina Roll-In Slimewalls®, Cleave Edge och Carbon Array™ 3 för att ge den här brädan gott om pop samtidigt som den förblir lyhörd och slitstark. "
                + "Rätt bräda för alla: Warpig XS är perfekt för damer och herrar som förstår att det inte är storleken på trollstaven som är viktig, utan hur man utövar sin magi.", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
       persist(new Product("snowboard", "Burton", "Chicklet 120", 120, "The Burton Chicklet™ is the ticket for girls who want to start snowboarding and quickly learn the basics. "
               + "What makes it so perfect is the beginner-friendly combo of a catch-free convex base and the softest flex possible so that even the lightest weight riders can master turning and stopping."
               + "No camber or rocker here, just a flat profile from nose to tail that’s extra stable for better balance and board control. Attach the Riglet accessory to the nose or tail to tow her around and "
               + "get her comfortable until she's ready to rock bindings. Snowboarding is for fun…so are colourful patterns. Just as flowers and butterflies dance in the breeze,"
               + " your sweet little girl will float atop this board with ease.", 1890, 1890,  "/resources/images/Chicklet1152019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "Custom Smalls", 140, "DEN MÅNGSIDIGA ALL-MOUNTAIN BRÄDAN FÖR UNGA ÅKARE\n" +
"Burton Custom är en riktig klassiker och en riktigt mångsidig bräda. Burton Customs Smalls är barnversionen av den här klassikern och den perfekta brädan för unga åkare som vill ha allt! Park, pist och puder - den här brädan garanterar massor av skoj i snön.\n" +
"Custom Smalls är gjord för barn som shreddar allt från park, rails och puder. Den här brädan ger dig massor av möjligheter för freestyleprogression för nybörjare och medelgoda åkare till en högvärdig kvalitet.\n" +
"Custom Smalls har en twin shape som är perfekt symmetrisk för en balanserad åkning som är lika mångsidig oavsett om den åks normalt eller switch. Jibba, snurra, stompa, och gör butters med större balans av freestylerörlighet och kattlik stabilitet oavsett vilket håll du pekar den åt. Pro-Tip® är en avsmalnande tjocklek av tip och tail som reducerar svingvikten för lättare rörlighet.\n" +
"En evolution av den traditionella camberformen, PurePop camberprofilen har subtila flata zoner precis utanför dina fötter för att förstärka pop och tillföra lekfullhet till den kvicka och lyhörda cambern. Tidig upphöjning av sektionerna i tip och tail ökar flytkraften med en huggfri känsla.\n" +
"Twin flex är perfekt symmetrisk för en balanserad åkning som är lika mångsidig oavsett om den åks normalt eller switch.\n" +
"Lättare och med massor av pop, FSC® Certified Super Fly II® 800 g kärnan är en träkärna med två olika träslag som använder sig av vertikala lamineringar med dubbel densitet med växlande hårt och mjukt trä. Det minskar den totala vikten utan att offra styrka eller prestanda. DUALZONE® EGD®, träfibrerna är placerade längst tå- och hälkanterna på två sammanhängande zoner, vinkelrätt mot resten av träkärnan, för kontinuerligt kantgrepp och ökad styrka. Biax® glasfiber har en jibbvänlig, mjuk vridstyv flex och en förlåtande och parkvänlig känsla. Den här brädan har Super Sap® epoxi, vilket är ett harts formulerat med biobaserade material som minskar koldioxidutsläppen med 50 % jämfört med konventionella, helt oljebaserade epoxis. Mindre oljekonsumtion innebär ännu mindre koldioxidavtryck per bräda. Den extruderade basen är snabb och kräver inte mycket underhåll.\n" +
"Starkare, snabbare, enklare och mer justerbar - Channel® ger dig total kontroll över din stance och din bräda i en design som är kompatibel med alla större bindningssystem (inte bara Burtons). I själva verket litar Burton på den så mycket att alla brädor med The Channel backas upp av tre års garanti.", 3490, 3490,  "/resources/images/CustomSmalls1402019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "Process 162", 162, "Ta din freestyleåkning längre med brädan som Mark McMorris väljer för lätt prestation och action-laddad pop. Nyutvecklad för att spara vikt och förstärka pop, Mark McMorris väljer Burton Process tack vare dess twin freestyle lekfullhet och skicklighet i varje terräng. Den avslappnade och flytande Flying V gör att du känner dig hemma i alla terränger. Lite mjukare än Burton Custom, The Process är ditt val för en freestyle-all mountain bräda.", 4490, 3490,  "/resources/images/Process1622019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "Air Retro 156", 156, "A retro spin on the Family Tree’s fastest new addition, inspired by snowboarding’s signature event: the banked slalom.\n" +
"The Burton designers called it “the banked slalom board” from day one, so you know it’s built to turn. The Speed Date features a special blend of Balanced Freeride Geometry that thrives on all terrain. Directional Camber combines with a unique shape, aggressive sidecut and just the right amount of taper to burn turns and eat berms for breakfast.", 3490, 3490,  "/resources/images/SpeedDateRetro1562019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "TWC Pro 156", 156, "This is your ticket to fly. The Burton TWC Pro snowboard is an all-mountain deck that gives you the snap, control, and response to propel your snowboarding skills forward. Design by the famous snowboarder and skateboarder Shaun White, this is the very same board he used during his supreme pipe runs and park laps. No matter what condition you are in, this board excels when you push it to its limits.\n" +
"\n" +
"Under the hood you will find a Traditional Camber profile. That provides the pop and stability you will need to go big on the pipe and to perfect your jumps. Compared to a twin shaped board this Directional Shape gives more power, while still handling switching and jumping really well. The board’s narrow waist gives it an extremely response feel while going quickly from edge-to-edge. Its core feels light and snappy, allowing you take on some of the biggest freestyle features on the mountain.\n" +
"\n" +
"Overall the Burton TWC Pro snowboard is a fun but wild animal that only experienced riders will be able to tame. For those of us who prefer an even more lively, stable, and aggressive board", 499990, 499990,  "/resources/images/BurtonTWCPro.jpg"));
        
        persist(new Product("snowboard", "Capita", "Children OTG", 149, "Inspirerad av den prisvinnande DOA serien, Children of the Gnar har en nästa generations hybrid camber design som ger pop och respons som hos en camberbräda med den förlåtande känslan hos en rocker. Även om denna serie är väldigt förlåtande och uppmuntrar utveckling är det ingen leksak. Gnar har en bredare mitt för att ge utrymme till större skostorlekar hos mindre åkare och den progressiva geometrin ger en snabb inlärningskurva i backen.", 3190, 3190,  "/resources/images/ChildrenOfTheGnar149Snowboardresized.jpg"));
        persist(new Product("snowboard", "Ride", "Compact 154", 154, "“Don’t Hassle Me, I’m Local.”\n" +
"\n" +
"The Compact returns for another season with an all-new shape, but still sports the all mountain versatility that riders keep coming back for. Rocker makes riding powder effortless and also allows for a catch-free ride in firmer snow conditions. The board profile flattens out underfoot for a stable flat zone that provides consistent tracking for added confidence when you’re riding fast. The softer flex makes initiating turns easier and Slimewalls to absorb vibration and add durability. The Compact combines performance features with a soft-flexing feel for a board that is fun in any condition, for any ability level.", 3890, 3890,  "/resources/images/Compact1542019Snowboard.jpg"));
        persist(new Product("snowboard", "Burton", "Custom FV 154", 1154, "DEN MEST BETRODDA OCH MÅNGSIDIGA ALL-TERRÄNG BRÄDAN MED FLYING V PROFIL\n" +
"Ända sedan dess ödmjuka början har innovation definierat Burton Custom serien och utskiljer den som den mest populära, mångsidiga, och efterliknade brädan på snowboardscenen. Sedan 1996 har den här ikonen härskat och konstant utvecklats med en beprövad formel som kombinerar tidsbeprövad design med drivande ingredienser för att skapa en lätt, poppig och väldigt mångsidig bräda. Custom Flying V kombinerar kraften hos traditionell camber med det avslappnade flytet hos rocker för det bästa av två världar.\n" +
"\n" +
"Åkstil\n" +
"Pister, park, puder - Custom Flying V är redo för nästan allting. Oavsett om du åker en dag eller varje dag under månha år, den här brädan lämpar sig för alla.\n" +
"\n" +
"Form\n" +
"Riktad Form: den klassiska snowboardformen, framtagen för att åkas med en något längre nose än tail för att koncentrera pop i tailen samtidigt som du får gott om flytkraft, jämnhet och kontroll för ös i varje terräng och förhållande. Långt innan hypen började kring de vågformade kanterna introducerade Burton frostbite kanter. Frostbitekanter sträcker sig ut något under dina bindningar för enorma kant grepp om hårda, isiga förhållanden. Kraftfull när du behöver det och lekfull när du inte gör det. Pro-Tip® är en avsmalnande tjocklek av tip och tail som reducerar svingvikten för lättare rörlighet.\n" +
"\n" +
"Profil\n" +
"En blandning av camber och rocker, Flying V erbjuder det bästa av båda världar. Rocker över det hela brädan, även mellan och utanför fötterna, ökar lekfullhet och flyt. Subtila camberzoner under dina fötter fokuserar på kantkontroll för skarp kvickhet, ökad pop och kraftfulla svängar.\n" +
"\n" +
"Flex\n" +
"Twin flex är perfekt symmetrisk från tip till tail för mångsidighet oavsett om du åker normalt eller switch.\n" +
"\n" +
"Konstruktion\n" +
"FSC® Certified Super Fly II® 700 kärna har starkare och lättare träslag inriktade på specifika områden av kärnan för att ge pop, styrka och minskad vikt. DUALZONE® EGD®, Träfibrerna är placerade längst tå- och hälkanterna på två sammanhängande zoner, vinkelrätt mot resten av träkärnan, för kontinuerligt kantgrepp och ökad styrka. Squeezebox profilen förbättrar pop och prestanda genom balansen av tjockare, mer kraftfulla kärnsektioner med tunnare, mer flexibla sektioner. Åkarens energi överförs utåt från under fötterna, aktiverar tip och tail samtidigt som brädan blir kvickare, mer stabil och lättare att hantera. 45 graders Carbon Highlights optimerar varje lager i fiberglasblandningen och lägger till ett helt tip-to-tail kolfiberlager för att minska vikt och finjustera känslan för att skapa olika åkegenskaper. En 45° fibervinkel ger en mer aggressiv åkning. På den sintrade WFO basen är vaxet arbetat djupt in i porerna hos detta extremt absorberande, täta material. Resultatet är en extremt tålig bas som förblir vidöppen i alla väder hela säsongen.\n" +
"Starkare, snabbare, enklare och mer justerbar - Channell® ger dig total kontroll över din stans och din bräda i en design som är kompatibel med alla större bindningssystem (inte bara Burtons). I själva verket litar Burton på den så mycket att alla brädor med The Channel backas upp av tre års garanti. Den Burtonexklusiva Infinite Ride®  tekniken låter Burton maximera pop och styrka genom att förbygga brädan och sedan sätta in den i en maskin som \"åker in\" brädan åt dig. Din bräda kommer att bibehålla sin flex, pop och känsla från första dagen framåt, säsong efter säsong.", 6290, 6290,  "/resources/images/CustomFlyingV1542019Snowboardresized.jpg"));
        
        persist(new Product("snowboard", "Bataleon", "Distortia 149", 149, ".Å GUD, VARFÖR KAN INTE MIN POJKVÄN ÅKA SNOWBOARD?\n" +
"Parker är en förvrängd verklighet.. för vissa människor åtminstone. Med Bataleon Distortia kan damerna vrida och justera denna verklighet till fullo. Denna bräda är stark och flexibel och gör både rails och kickers till en kul grej.\n" +
"\n" +
"Åkstil\n" +
"En bräda som Real Park chicks älskar! The Distortia är tillräckligt flexibel för jibs men ändå tillräckligt stark för att stompa de stora hoppen.\n" +
"\n" +
"Shape\n" +
"I parken finns det bara en sann shape och det är true twin. Distortia har så klart Twin 3 BT Shape med symmetrisk tip och tail. Balans och stabilitet oavsett vilket håll du hoppar eller landar åt. Nya SideKick Tips ökar kanternas upphöjning på de bredaste punkterna vid nose och tail för enklare svänginledning.\n" +
"\n" +
"Profil\n" +
"Twin 3BT består av en relativt bred mittbas och en lätt upphöjning vid kanterna. Detta hjälper dig att hålla kontrollen när du pressar eller åker fort. Den mjuka cambern är perfekt doserad för flexibel railåkning och tillräckligt poppig för dem stora hoppen.\n" +
"\n" +
"Flex\n" +
"Det jämna flexet är perfekt för parkåkning. Enkla jib och hopp med en mjuk men stabil bräda.\n" +
"\n" +
"Konstruktion\n" +
"En poppelträkärna med bokträbalkar längst insatserna och en sandwichkonstruktion med biaxiellt glasfiber. Det innebär grymma press- och popegenskaper plus snabb manövrerbarhet. X-formade kolstycken under bindningarna ger en exakt kraftöverföring från kant till kant. Den extruderade Super Slick X basen är väldigt slitstark, snabb och kräver minimal skötsel!", 4690, 4690,  "/resources/images/BataleonDistortia1492019Snowboard.jpg"));
        persist(new Product("snowboard", "Capita", "Jess Kimura", 150, "Jess Kimura är en av de bästa snowboardåkarna det här årtiondet och har gjort avtryck inte bara på den kvinnliga scenen utan på snowboarding som helhet. En nose med rocker på Jess Kimura Pro gör brädan enklare att jibba med och ökar flytkraften i pudret. Samtidigt som positiv camber ger stabiliteten som du behöver för att stompa stora trick i utmanande terräng.\n" +
"\n" +
"Åkstil\n" +
"En högavancerad freestyle snowboard för medelbra till avancerade åkare - från rails och hopp i parken och booters i offpisten till streetrails på kvällen, denna bräda fixar allt.\n" +
"\n" +
"Shape\n" +
"Den perfekt symmetriska twinformen ger dig en balanserad åkning som är lika mångsidig oavsett om den åks normalt eller switch.\n" +
"\n" +
"Profil\n" +
"Capitas Alpine V1 profil har camber i mitten med en plan tail och en rocker i nosen. Detta tillåter massor av pop och stabilitet på hårt packad snö samtidigt som det ökar flytkraften i puder.\n" +
"\n" +
"Flex\n" +
"En medium flex ger flex och lyhördhet när du jibbar, utan att offra stabilitet och pop du behöver för högre hastigheter och större gaps.\n" +
"\n" +
"Konstruktion\n" +
"Canadian Maple Dual Core™ har inre poppelinlägg med lönn i området kring bindningarna och längs kanterna. Det är en tät och kraftfull kärna byggd för hållbarhet och kontroll. Två 30 mm breda kolfiberinlägg längs hela brädans längd ökar kraften och responsen samtidigt som brädan håller en låg vikt. Den sintrade Superdrive™ basen har en unik och eftertraktad balans mellan hög prestanda och låga underhållskrav.", 4890, 4890,  "/resources/images/CapitaJessKimuraPro1502019Snowboard.jpg"));
        persist(new Product("snowboard", "Bataleon", "Minishred 100", 100, "MINISHRED SNOWBOARD OCH MINI SHRED BINDNING FÖR BARN FRÅN BATALEON\n" +
"Inga kompromisser, Mini Shred är ett riktigt snowboardset och inte en leksak\n" +
"\n" +
"Bataleon Mini Shred Snowboard\n" +
"Tillverkad på samma sätt som Bataleon tillverkar alla sina brädor, med en träkärna från tip till tail. De förlåtande fördelarna hos 3BT™ hjälper barn att utvecklas snabbare tack vare huggfria kanter samtidigt som den ger dem möjligheten att lära sig att carva med låg hastighet. Snart kommer du cruisa ner för backen tillsammans med dina små.\n" +
"\n" +
"Bataleon Mini Shred bindning\n" +
"Den bekväma Mini Shred bindningen för barn ger ett paket med mjukare flex och noll kompromisser för pålitlig prestanda i varje terräng. Ergonomiska bakkappor slår sig samman med högvärdiga straps och gott om dämpning för att ge barnen komforten och prestandan som krävs för en heldag i backen. ", 1890, 1890,  "/resources/images/BataleonMinishred100MiniShredBdg2019Snowboardpaket.jpg"));
        persist(new Product("snowboard", "Bataleon", "Push UP 149", 149, "DET ÄR INTE BARA KILLAR SOM VRIDER PÅ HUVUDET NÄR DE SER EN SÅN HÄR PUSH UP\n" +
"Push Up är för tjejer som inte behöver push-ups för att visa vad som gäller. De krossar helt enkelt hela berget. Detta är en solid freestylebräda med en tydligare camber för park, pist och puder.\n" +
"\n" +
"Åkstil\n" +
"Push Up känns lika bra i parken som i puder och pist.\n" +
"\n" +
"Shape\n" +
"En mångsidig bräda som Push Up behöver Freestyle 3BT. Nosen är 1 cm längre än tailen och sidoskärningsradien är smalare. Detta resulterar i mer flyt plus mer grepp. Nya SideKick Tips ökar kanternas upphöjning på de bredaste punkterna vid nose och tail för enklare svänginledning, mer flyt och bättre hantering i ojämn terräng.\n" +
"\n" +
"Profil\n" +
"Centerbasen och sidobaserna på Freestyle 3BT är lika breda och kantupphöjningen är lite högre än på twins. En medium camber som är perfekt doserad för flexibel railåkning och tillräckligt poppig för de stora hoppen.\n" +
"\n" +
"Flex\n" +
"Medium flex är perfekt för jibb i parken och ös i puder. Den är superflexig och riktigt lekfull.\n" +
"\n" +
"Konstruktion\n" +
"Poppel och paulownia träkärna förstärkt med bi-axial fiberglas. Det innebär grymma press- och popegenskaper plus snabb manövrerbarhet. Kolstycken mellan bindningarna ger en exakt kraftöverföring och lite extra kraft för ollies. Den extruderade Super Slick X basen är väldigt slitstark, snabb och kräver minimal skötsel - och den är transparent för att låta grafiken lysa!", 4890, 4890,  "/resources/images/BataleonPushUp1492019Snowboard.jpg"));
        persist(new Product("snowboard", "Ride", "Saturday 146", 146, "Saturday från Ride har allt för att göra en bra dag på berget ännu bättre. Twin Hybrid Rocker kombineras med en medium flex för en bräda som gör att du kan bli vild och galen i varje terräng. Formen och profilen erbjuder mycket float och mångsidighet för de branta och djupaste linjerna du kan hitta. Varje dag är lördag när du är på berget.!", 4490, 4490,  "/resources/images/RideSaturday1462019Snowboard.jpg"));
        persist(new Product("snowboard", "Capita", "Space M Fantasy", 149, "EN AV DE BÄSTA SNOWBOARDS FÖR FREESTYLE OCH PARK FÖR TJEJER - CAPITA SPACE METAL FANTASY\n" +
"Den fyrfaldigt Transworld Good Wood prisbelönta Space Metal Fantasy anses vara en av de bästa parkbrädorna för kvinnor på marknaden. Den väldigt förlåtande rockerprofilen ger en rolig upplevelse för intermediära åkare som vill påskynda sin inlärningskurva och ta sin åkning till nästa nivå. Space Metal Fantasy är klassad som en parkbräda men är rolig att carva omkring med på pisterna, leka med i parken och den flyter även bra i puder.\n" +
"\n" +
"Åkstil\n" +
"En freestyle och parkbräda för åkare på alla nivåer - den här brädan är rolig överallt. Oavsett om du carvar på pisten eller jibbar i parken. Dessutom flyter den riktigt bra i puder.\n" +
"\n" +
"Shape\n" +
"Den perfekt symmetriska twinformen ger dig en balanserad åkning som är lika mångsidig oavsett om den åks normalt eller switch.\n" +
"\n" +
"Profil\n" +
"Freedom V2 profilen har flat camber mellan bindningarna och en stor rocker i nose och tail. Den här brädan svänger väldigt lätt och ger en lekfull, surfig åkning. Att få kanthugg är nästan omöjligt.\n" +
"\n" +
"Flex\n" +
"Mjukt-medium flex ger lätta svängar och garanterar ett komfortabelt åk i all terräng, oavsett om det är i park, puder, pist eller allt där i mellan.\n" +
"\n" +
"Konstruktion\n" +
"FSC® Certified Select Core™ är en väldigt poppig och lätt populuskärna för ett jämnt flex. XXX(TRUDED)™ basen är superhård och extremt hållbar. Detta är inte en vanlig extruderad bas. Biaxialt fiberglas över och under kärnan är impregnerat med vegetabiliskt, högpresterande Magic Bean™ resin för en hållbar och stabil konstruktion.", 3990, 3990,  "/resources/images/CapitaSpaceMetalFantasy1492019Snowboard.jpg"));
        persist(new Product("snowboard", "Capita", "Ultrafear 151", 151, "SÅ GOTT SOM OFÖRSTÖRBAR PARK/JIBBRÄDA OCH TREFALDIG GOOD WOOD AWARD VINNARE - CAPITA ULTRAFEAR\n" +
"Med Capita Ultrafear behöver du inte vara rädd för några hinder, men hindren borde vara rädda för dig. Tekniker som P2 Superlight Core™ och Kevlar/Titanal Body Armor™ har kombinerats för att skapa ett oförstörbart vapen i din arsenal. Den här snowboarden är för alla som inte vill bli tillsagda vart de ska åka och som letar efter en bräda som kan fly undan standarder.\n" +
"\n" +
"Åkstil\n" +
"En bräda med hög prestanda för jibb i park och urban terräng. En väldigt robust, mångsidig design möter kraven hos avancerade åkare.\n" +
"\n" +
"Shape\n" +
"Den perfekt symmetriska twinformen ger dig en balanserad åkning som är lika mångsidig oavsett om den åks normalt eller switch.\n" +
"\n" +
"Profil\n" +
"Freedom V1 profilen har flat camber mellan bindningarna och en jämn rocker i nose och tail. Brädan svänger väldigt enkelt men är även stabil i högre hastigheter. Att få kanthugg är nästan omöjligt.\n" +
"\n" +
"Flex\n" +
"En medium flex är perfekt för mångsidig freestyleåkning, tillräckligt hård för att köra på obstacles med högre hastigheter men samtidigt tillräckligt mjuk för att vara lekfull vid låga hastigheter.\n" +
"\n" +
"Konstruktion\n" +
"P2 Superlight Core™ är en extremt lätt och mångsidig kärna, kombinerade med hög stötdämpning och maximal pop. Insatser med Kevlar/Titanal Body Armor™ strävor har intregrerats i frästa kanaler i kärnan för strukturell förstärkning. 1,5 mm korkdämpningen längs kanterna är väldigt slitstark och lättare än gummi, kork ökar skyddet från hårda smällar. Den sintrade Superdrive™ basen har en unik och eftertraktad balans mellan hög prestanda och låga underhållskrav.", 5290, 5290,  "/resources/images/CapitaUltrafear1512019Snowboard.jpg"));
        
        persist(new Product("boot", "32", "Lashed Melancon", 42, "SNYGGA BOOTS FÖR HELA BERGET\n" +
"Desiree Melancon är mest känd för sin hårda urbana shredding. Metall, betong, trä - oavsett vad som kommer i vägen, sänder hon det. Men liksom nästan alla andra snowboarders är hon också glad över färsk snö. Därför har hon en medium hård boot som känns bekväm på hela berget: 32 Lashed.", 3490, 3490, "/resources/images/32LashedMelancon2019Snowboardboots.jpg"));
        persist(new Product("boot", "32", "Jones MTB", 42, "Jones MTB boot från Thirtytwo är unik. Den har de perfekta egenskaperna för oslagbar offpistsnowboardåkning. För åkare som är beredda att göra allt som krävs för att shredda helt orörda backar är detta den perfekta booten. Den har alla egenskaper som krävs för att campa, tura och gå djupare och längre än någonsin med din splitboard.", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "32", "X BT Light", 42, "Grym freestyleprestanda med låg vikt, Light har allt JP Walkers fötter önskar\n" +
"Light Boot från 32 är designad, testad och beprövad av den levande legenden JP Walker och är en del av hans signaturkollektion. Skaftets ledade konstruktion ger booten en jämn medium/mjuk flex som gör Light perfekt för freestyleåkning. 1:1 Lasting Technology gör det möjligt att tillverka pjäxor med den bästa passformen på marknaden och minskar de yttre dimensionerna. Comfort Fit foder är värmeformbart och har sitt eget snörningssystem för att garantera perfekt hälpasform. ", 3490, 3490, "/resources/images/32XBlueTomatoLight2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Grom Boa", 29, "Now even the youngest riders can experience the fast-lacing convenience of the Boa® closure system. For the kid that just learned the first turns, or is growing out of the Velcro® Mini-Grom boot, the Burton Grom Boa® boot delivers comfort and a mellow flex that’s great for learning on the bunny hill or stepping it up to a bigger terrain. With a quick turn of the dial, kids can fine-tune their fit and comfort without help from mom or dad, and open the door to a whole new world of snowboarding independence. ", 1290, 1290, "/resources/images/BurtonGromBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Ion Boa Black", 42, "Modellen som sätter ribban högre i varje disciplin, Burton Ion Boa® är utrustad med Boa® Focus-snörning, toppunkten för en optimal passform. Byggd för att ladda hårt över hela berget, The Ion är legendarisk för sitt staplade techpaket uppgraderat med Burtons Life Liner för lättvikt och förbättrad återhämtning. Ingenting står i vägen för långa dagar på berget med Ion.", 4590, 4590, "/resources/images/BurtonIonBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Limelight Boa", 39, "Med Boa snörningssystemet kommer du vara den första som drar ut i backen med en säker passform för att shredda allt från skogsgläntor till pister. Den kvinnospecifika True Fit® designen försäkrar en mer harmonisk anslutning eftersom varje element hos denna boot har designats och konstruerats kring kvinnor. Total Warmth är en kombination av en 3M® Thinsulate® isolerad innersko och Sleeping Bag värmereflekterande folie. En totalt sett mjuk och förlåtande flex för ihop allt i en boot som ger dig mer nöje och frihet vid varje åk.", 3090, 3090, "/resources/images/BurtonLimelightBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Photon Boa", 42, "Burton Photon Boa® är för dem som vill glida med komfort och använda det precisa klicket i Boa® Coiler closure systemet. Hard chargers som föredrar respons som känns gjuten till booten och uppskattar kraften i Lockdown Lacing. Få det grepp som krävs för att vandra ut ur parken och ut i sidecountrt med Vibram® yttersulan och B3 Gel dämpning. Sovsäckens Reflekterande folie återspeglar värmen tillbaka till fötterna för att hålla dig ute på berget så länge du vill.", 3790, 3790, "/resources/images/BurtonPhotonBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Ruler Speed Z", 42, "Snowboardbootsen Ruler från Burton är något styvare boots för åkaren som gillar lite mer tryck under fötterna och att ta ut svängarna över hela berget. Innerskon Imprint 2 formar sig lätt efter foten för ökad komfort och för högsta möjliga känsla i svängarna. Även försedda med en stötdämpande DynoGRIP-yttersula och Sleeping Bag Reflective Foil som reflekterar värmen tillbaka till fötterna.", 2790, 2790, "/resources/images/BurtonRulerSnowboardboots.jpg"));
        persist(new Product("boot", "Ride", "Lasso  Black", 42, "Ride Lasso har det effektivaste och snabbare snörningssystemet. Med Boa® stängningssystemet och Tongue Tied™ stängningssystemet kommer du vara redo att åka medan dina polare kämpar på med sina blöta snören. Den hårdare flexen gör Lasso perfekt för medelgoda åkare som letar efter en mer lyhörd all-mountain/freestyleboot. De tekniska egenskaperna och totala prestandan låter dig åka på hela berget med en lyhörd och exakt känsla och passform.", 3190, 3190, "/resources/images/RideLasso2019Snowboardboots.jpg"));
        persist(new Product("binding", "Burton", "Lexa",42,"Med legendarisk freestyleprestanda är det inte konstigt att Lexa är förstahandsvalet för en hel rad med proffsåkare från Burton (och som inte åker för Burton). Double Take spännen med Insta-Click låter dig stänga bindningarna snabbt och droppa in först. Remmarna i kombination med en stödjande Heel Hammock i bakkappan kramar din boot så att du kan köra med dina straps lite lösare. Experimentera med bakkappans Zero Forward Lean för en mer lekfull känsla samtidigt som du tar hand om din kropp med den anatomiska komforten hos Re:Flex AutoCANT FullBED dämpning. True Fit® betyder att varje element hos varje del av din setup har designats och konstruerats för att passa åktstilen hos tjejer. För när det passar bra går det bättre att åka.", 3490, 3490,"/resources/images/BurtonLexa2019Snowboardbindningar.jpg"));
        persist(new Product("binding", "Burton", "Mission",42,"Legendariska åkare har valt Mission i över tio års tid för att den helt enkelt krossar allt motstånd när det kommer till komfortabel respons som du kan lita på. Arbetshästen i Burtons bindningskollektion, den har proffsfinesser som ergonomiska bakkappor med zero Forward Lean och utan tvekan de bästa strapsen och spännena i branschen. Förlita dig på Re:Flex® och börja ditt Mission!", 3490, 3490,"/resources/images/BurtonMission2019Snowboardbindningar.jpg"));
        persist(new Product("binding", "Drake", "Fifty",42,"Drake Fifty har funnits på marknaden i över 10 år och är fortfarande den prisvärde ledaren. Otaliga åkare har förlitat sig på Drake Fifty eftersom den ständigt överträffar nykomlingar med sin komfort och lyhördhet. Natural Cant interface förbättrar din åkställning och låter dig ta dig till nya prestationsnivåer i parken och på hela berget.", 3490, 3490,"/resources/images/DrakeFifty2019Snowboardbindningar.jpg"));
        persist(new Product("binding", "Drake", "Queen",42,"Fully equipped, the Queen is the entry level binding you want with all the features you need. The top quality materials are the perfect option for just getting into snowboarding. The Queen offers superior foothold and with its forgiving flex, maintains an easy and smooth ride to allow you to improve your riding at an increased rate. MFC comfort straps complete the package of Women’s entry level binding royalty.", 3490, 3490,"/resources/images/DrakeQueen2019Snowboardbindningar.jpg"));
        
    }
    
}
