package pt.utl.ist.tese.pca;

import java.util.ArrayList;

public class StatisticalSampleData {

	public static final double[][] SAMPLE_DATA_SAD ={ 
		{ 2.5d , 2.4d },	{ 0.5d , 0.7d },	{ 2.2d , 2.9d },	{ 1.9d , 2.2d },
		{ 3.1d , 3.0d },	{ 2.3d , 2.7d },	{ 2.0d , 1.6d },	{ 1.0d , 1.1d },
		{ 1.5d , 1.6d },	{ 1.1d , 0.9d }};


	public static final double[][] SAMPLE_DATA_3D_HUGE_LINE ={	
		{001d,001d,001d}	,{002d,002d,002d}	,{003d,003d,003d}	,{004d,004d,004d}	,{005d,005d,005d}	,
		{006d,006d,006d}	,{007d,007d,007d}	,{008d,008d,008d}	,{009d,009d,009d}	,{010d,010d,010d}	,
		{011d,011d,011d}	,{012d,012d,012d}	,{013d,013d,013d}	,{014d,014d,014d}	,{015d,015d,015d}	,
		{016d,016d,016d}	,{017d,017d,017d}	,{018d,018d,018d}	,{019d,019d,019d}	,{020d,020d,020d}	,
		{021d,021d,021d}	,{022d,022d,022d}	,{023d,023d,023d}	,{024d,024d,024d}	,{025d,025d,025d}	,
		{026d,026d,026d}	,{027d,027d,027d}	,{028d,028d,028d}	,{029d,029d,029d}	,{030d,030d,030d}	,
		{031d,031d,031d}	,{032d,032d,032d}	,
		{033d,033d,033d}	,{034d,034d,034d}	,{035d,035d,035d}	,{036d,036d,036d}	,{037d,037d,037d}	,
		{038d,038d,038d}	,{039d,039d,039d}	,{040d,040d,040d}	,{041d,041d,041d}	,{042d,042d,042d}	,
		{043d,043d,043d}	,{044d,044d,044d}	,{045d,045d,045d}	,{046d,046d,046d}	,{047d,047d,047d}	,
		{048d,048d,048d}	,{049d,049d,049d}	,{050d,050d,050d}	,{051d,051d,051d}	,{052d,052d,052d}	,
		{053d,053d,053d}	,{054d,054d,054d}	,{055d,055d,055d}	,{056d,056d,056d}	,{057d,057d,057d}	,
		{058d,058d,058d}	,{059d,059d,059d}	,
		{060d,060d,060d}	,{061d,061d,061d}	,{062d,062d,062d}	,{063d,063d,063d}	,{064d,064d,064d}	,
		{065d,065d,065d}	,{066d,066d,066d}	,{067d,067d,067d}	,{068d,068d,068d}	,{069d,069d,069d}	,
		{070d,070d,070d}	,{071d,071d,071d}	,{072d,072d,072d}	,{073d,073d,073d}	,{074d,074d,074d}	,
		{075d,075d,075d}	,{076d,076d,076d}	,{077d,077d,077d}	,{078d,078d,078d}	,{079d,079d,079d}	,
		{080d,080d,080d}	,{081d,081d,081d}	,{082d,082d,082d}	,{083d,083d,083d}	,{084d,084d,084d}	,
		{085d,085d,085d}	,{086d,086d,086d}	,
		{087d,087d,087d}	,{088d,088d,088d}	,{089d,089d,089d}	,{090d,090d,090d}	,{091d,091d,091d}	,
		{092d,092d,092d}	,{093d,093d,093d}	,{094d,094d,094d}	,{095d,095d,095d}	,{096d,096d,096d}	,
		{097d,097d,097d}	,{098d,098d,098d}	,{099d,099d,099d}	,{100d,100d,100d}	,{101d,101d,101d}	,
		{102d,102d,102d}	,{103d,103d,103d}	,{104d,104d,104d}	,{105d,105d,105d}	,{106d,106d,106d}	,
		{107d,107d,107d}	,{108d,108d,108d}	,{109d,109d,109d}	,{110d,110d,110d}	,{111d,111d,111d}	,
		{112d,112d,112d}	,{113d,113d,113d}	,
		{114d,114d,114d}	,{115d,115d,115d}	,{116d,116d,116d}	,{117d,117d,117d}	,{118d,118d,118d}	,
		{119d,119d,119d}	,{120d,120d,120d}	,{121d,121d,121d}	,{122d,122d,122d}	,{123d,123d,123d}	,
		{124d,124d,124d}	,{125d,125d,125d}	,{126d,126d,126d}	,{127d,127d,127d}	,{128d,128d,128d}	,
		{129d,129d,129d}	,{130d,130d,130d}	,{131d,131d,131d}	,{132d,132d,132d}	,{133d,133d,133d}	,
		{134d,134d,134d}	,{135d,135d,135d}	,{136d,136d,136d}	,{137d,137d,137d}	,{138d,138d,138d}	,
		{139d,139d,139d}	,{140d,140d,140d}	,
		{141d,141d,141d}	,{142d,142d,142d}	,{143d,143d,143d}	,{144d,144d,144d}	,{145d,145d,145d}	,
		{146d,146d,146d}	,{147d,147d,147d}	,{148d,148d,148d}	,{149d,149d,149d}	,{150d,150d,150d}	,
		{151d,151d,151d}	,{152d,152d,152d}	,{153d,153d,153d}	,{154d,154d,154d}	,{155d,155d,155d}	,
		{156d,156d,156d}	,{157d,157d,157d}	,{158d,158d,158d}	,{159d,159d,159d}	,{160d,160d,160d}	,
		{161d,161d,161d}	,{162d,162d,162d}	,{163d,163d,163d}	,{164d,164d,164d}	,{165d,165d,165d}	,
		{166d,166d,166d}	,{167d,167d,167d}	,
		{168d,168d,168d}	,{169d,169d,169d}	,{170d,170d,170d}	,{171d,171d,171d}	,{172d,172d,172d}	,
		{173d,173d,173d}	,{174d,174d,174d}	,{175d,175d,175d}	,{176d,176d,176d}	,{177d,177d,177d}	,
		{178d,178d,178d}	,{179d,179d,179d}	,{180d,180d,180d}	,{181d,181d,181d}	,{182d,182d,182d}	,
		{183d,183d,183d}	,{184d,184d,184d}	,{185d,185d,185d}	,{186d,186d,186d}	,{187d,187d,187d}	,
		{188d,188d,188d}	,{189d,189d,189d}	,{190d,190d,190d}	,{191d,191d,191d}	,{192d,192d,192d}	,
		{193d,193d,193d}	,{194d,194d,194d}	,
		{195d,195d,195d}	,{196d,196d,196d}	,{197d,197d,197d}	,{198d,198d,198d}	,{199d,199d,199d}	,
		{200d,200d,200d}	,{201d,201d,201d}	,{202d,202d,202d}	,{203d,203d,203d}	,{204d,204d,204d}	,
		{205d,205d,205d}	,{206d,206d,206d}	,{207d,207d,207d}	,{208d,208d,208d}	,{209d,209d,209d}	,
		{210d,210d,210d}	,{211d,211d,211d}	,{212d,212d,212d}	,{213d,213d,213d}	,{214d,214d,214d}	,
		{215d,215d,215d}	,{216d,216d,216d}	,{217d,217d,217d}	,{218d,218d,218d}	,{219d,219d,219d}	,
		{220d,220d,220d}	,{221d,221d,221d}	,
		{222d,222d,222d}	,{223d,223d,223d}	,{224d,224d,224d}	,{225d,225d,225d}	,{226d,226d,226d}	,
		{227d,227d,227d}	,{228d,228d,228d}	,{229d,229d,229d}	,{230d,230d,230d}	,{231d,231d,231d}	,
		{232d,232d,232d}	,{233d,233d,233d}	,{234d,234d,234d}	,{235d,235d,235d}	,{236d,236d,236d}	,
		{237d,237d,237d}	,{238d,238d,238d}	,{239d,239d,239d}	,{240d,240d,240d}	,{241d,241d,241d}	,
		{242d,242d,242d}	,{243d,243d,243d}	,{244d,244d,244d}	,{245d,245d,245d}	,{246d,246d,246d}	,
		{247d,247d,247d}	,{248d,248d,248d}	,
		{249d,249d,249d}	,{250d,250d,250d}	,{251d,251d,251d}	,{252d,252d,252d}	,{253d,253d,253d}	,
		{254d,254d,254d}	,{255d,255d,255d}	,{256d,256d,256d}	,{257d,257d,257d}	,{258d,258d,258d}	,
		{259d,259d,259d}	,{260d,260d,260d}	,{261d,261d,261d}	,{262d,262d,262d}	,{263d,263d,263d}	,
		{264d,264d,264d}	,{265d,265d,265d}	,{266d,266d,266d}	,{267d,267d,267d}	,{268d,268d,268d}	,
		{269d,269d,269d}	,{270d,270d,270d}	,{271d,271d,271d}	,{272d,272d,272d}	,{273d,273d,273d}	,
		{274d,274d,274d}	,{275d,275d,275d}	,
		{276d,276d,276d}	,{277d,277d,277d}	,{278d,278d,278d}	,{279d,279d,279d}	,{280d,280d,280d}	,
		{281d,281d,281d}	,{282d,282d,282d}	,{283d,283d,283d}	,{284d,284d,284d}	,{285d,285d,285d}	,
		{286d,286d,286d}	,{287d,287d,287d}	,{288d,288d,288d}	,{289d,289d,289d}	,{290d,290d,290d}	,
		{291d,291d,291d}	,{292d,292d,292d}	,{293d,293d,293d}	,{294d,294d,294d}	,{295d,295d,295d}	,
		{296d,296d,296d}	,{297d,297d,297d}	,{298d,298d,298d}	,{299d,299d,299d}	,{300d,300d,300d}	,
		{301d,301d,301d}	,{302d,302d,302d}	,
		{303d,303d,303d}	,{304d,304d,304d}	,{305d,305d,305d}	,{306d,306d,306d}	,{307d,307d,307d}	,
		{308d,308d,308d}	,{309d,309d,309d}	,{310d,310d,310d}	,{311d,311d,311d}	,{312d,312d,312d}	,
		{313d,313d,313d}	,{314d,314d,314d}	,{315d,315d,315d}	,{316d,316d,316d}	,{317d,317d,317d}	,
		{318d,318d,318d}	,{319d,319d,319d}	,{320d,320d,320d}	,{321d,321d,321d}	,{322d,322d,322d}	,
		{323d,323d,323d}	,{324d,324d,324d}	,{325d,325d,325d}	,{326d,326d,326d}	,{327d,327d,327d}	,
		{328d,328d,328d}	,{329d,329d,329d}	,
		{330d,330d,330d}	,{331d,331d,331d}	,{332d,332d,332d}	,{333d,333d,333d}	,{334d,334d,334d}	,
		{335d,335d,335d}	,{336d,336d,336d}	,{337d,337d,337d}	,{338d,338d,338d}	,{339d,339d,339d}	,
		{340d,340d,340d}	,{341d,341d,341d}	,{342d,342d,342d}	,{343d,343d,343d}	,{344d,344d,344d}	,
		{345d,345d,345d}	,{346d,346d,346d}	,{347d,347d,347d}	,{348d,348d,348d}	,{349d,349d,349d}	,
		{350d,350d,350d}	,{351d,351d,351d}	,{352d,352d,352d}	,{353d,353d,353d}	,{354d,354d,354d}	,
		{355d,355d,355d}	,{356d,356d,356d}	,
		{357d,357d,357d}	,{358d,358d,358d}	,{359d,359d,359d}	,{360d,360d,360d}	,{361d,361d,361d}	,
		{362d,362d,362d}	,{363d,363d,363d}	,{364d,364d,364d}	,{365d,365d,365d}	,{366d,366d,366d}	,
		{367d,367d,367d}	,{368d,368d,368d}	,{369d,369d,369d}	,{370d,370d,370d}	,{371d,371d,371d}	,
		{372d,372d,372d}	,{373d,373d,373d}	,{374d,374d,374d}	,{375d,375d,375d}	,{376d,376d,376d}	,
		{377d,377d,377d}	,{378d,378d,378d}	,{379d,379d,379d}	,{380d,380d,380d}	,{381d,381d,381d}	,
		{382d,382d,382d}	,{383d,383d,383d}	,
		{384d,384d,384d}	,{385d,385d,385d}	,{386d,386d,386d}	,{387d,387d,387d}	,{388d,388d,388d}	,
		{389d,389d,389d}	,{390d,390d,390d}	,{391d,391d,391d}	,{392d,392d,392d}	,{393d,393d,393d}	,
		{394d,394d,394d}	,{395d,395d,395d}	,{396d,396d,396d}	,{397d,397d,397d}	,{398d,398d,398d}	,
		{399d,399d,399d}	,{400d,400d,400d}	,{401d,401d,401d}	,{402d,402d,402d}	,{403d,403d,403d}	,
		{404d,404d,404d}	,{405d,405d,405d}	,{406d,406d,406d}	,{407d,407d,407d}	,{408d,408d,408d}	,
		{409d,409d,409d}	,{410d,410d,410d}	,
		{411d,411d,411d}	,{412d,412d,412d}	,{413d,413d,413d}	,{414d,414d,414d}	,{415d,415d,415d}	,
		{416d,416d,416d}	,{417d,417d,417d}	,{418d,418d,418d}	,{419d,419d,419d}	,{420d,420d,420d}	,
		{421d,421d,421d}	,{422d,422d,422d}	,{423d,423d,423d}	,{424d,424d,424d}	,{425d,425d,425d}	,
		{426d,426d,426d}	,{427d,427d,427d}	,{428d,428d,428d}	,{429d,429d,429d}	,{430d,430d,430d}	,
		{431d,431d,431d}	,{432d,432d,432d}	,{433d,433d,433d}	,{434d,434d,434d}	,{435d,435d,435d}	,
		{436d,436d,436d}	,{437d,437d,437d}	,
		{438d,438d,438d}	,{439d,439d,439d}	,{440d,440d,440d}	,{441d,441d,441d}	,{442d,442d,442d}	,
		{443d,443d,443d}	,{444d,444d,444d}	,{445d,445d,445d}	,{446d,446d,446d}	,{447d,447d,447d}	,
		{448d,448d,448d}	,{449d,449d,449d}	,{450d,450d,450d}	,{451d,451d,451d}	,{452d,452d,452d}	,
		{453d,453d,453d}	,{454d,454d,454d}	,{455d,455d,455d}	,{456d,456d,456d}	,{457d,457d,457d}	,
		{458d,458d,458d}	,{459d,459d,459d}	,{460d,460d,460d}	,{461d,461d,461d}	,{462d,462d,462d}	,
		{463d,463d,463d}	,{464d,464d,464d}	,
		{465d,465d,465d}	,{466d,466d,466d}	,{467d,467d,467d}	,{468d,468d,468d}	,{469d,469d,469d}	,
		{470d,470d,470d}	,{471d,471d,471d}	,{472d,472d,472d}	,{473d,473d,473d}	,{474d,474d,474d}	,
		{475d,475d,475d}	,{476d,476d,476d}	,{477d,477d,477d}	,{478d,478d,478d}	,{479d,479d,479d}	,
		{480d,480d,480d}	,{481d,481d,481d}	,{482d,482d,482d}	,{483d,483d,483d}	,{484d,484d,484d}	,
		{485d,485d,485d}	,{486d,486d,486d}	,{487d,487d,487d}	,{488d,488d,488d}	,{489d,489d,489d}	,
		{490d,490d,490d}	,{491d,491d,491d}	,
		{492d,492d,492d}	,{493d,493d,493d}	,{494d,494d,494d}	,{495d,495d,495d}	,{496d,496d,496d}	,
		{497d,497d,497d}	,{498d,498d,498d}	,{499d,499d,499d}	,{500d,500d,500d}	,{501d,501d,501d}	,
		{502d,502d,502d}	,{503d,503d,503d}	,{504d,504d,504d}	,{505d,505d,505d}	,{506d,506d,506d}	,
		{507d,507d,507d}	,{508d,508d,508d}	,{509d,509d,509d}	,{510d,510d,510d}	,{511d,511d,511d}	,
		{512d,512d,512d}	,{513d,513d,513d}	,{514d,514d,514d}	,{515d,515d,515d}	,{516d,516d,516d}	,
		{517d,517d,517d}	,{518d,518d,518d}	,
		{519d,519d,519d}	,{520d,520d,520d}	,{521d,521d,521d}	,{522d,522d,522d}	,{523d,523d,523d}	,
		{524d,524d,524d}	,{525d,525d,525d}	,{526d,526d,526d}	,{527d,527d,527d}	,{528d,528d,528d}	,
		{529d,529d,529d}	,{530d,530d,530d}	,{531d,531d,531d}	,{532d,532d,532d}	,{533d,533d,533d}	,
		{534d,534d,534d}	,{535d,535d,535d}	,{536d,536d,536d}	,{537d,537d,537d}	,{538d,538d,538d}	,
		{539d,539d,539d}	,{540d,540d,540d}	,{541d,541d,541d}	,{542d,542d,542d}	,{543d,543d,543d}	,
		{544d,544d,544d}	,{545d,545d,545d}	,
		{546d,546d,546d}	,{547d,547d,547d}	,{548d,548d,548d}	,{549d,549d,549d}	,{550d,550d,550d}	,
		{551d,551d,551d}	,{552d,552d,552d}	,{553d,553d,553d}	,{554d,554d,554d}	,{555d,555d,555d}	,
		{556d,556d,556d}	,{557d,557d,557d}	,{558d,558d,558d}	,{559d,559d,559d}	,{560d,560d,560d}	,
		{561d,561d,561d}	,{562d,562d,562d}	,{563d,563d,563d}	,{564d,564d,564d}	,{565d,565d,565d}	,
		{566d,566d,566d}	,{567d,567d,567d}	,{568d,568d,568d}	,{569d,569d,569d}	,{570d,570d,570d}	,
		{571d,571d,571d}	,{572d,572d,572d}	,
		{573d,573d,573d}	,{574d,574d,574d}	,{575d,575d,575d}	,{576d,576d,576d}	,{577d,577d,577d}	,
		{578d,578d,578d}	,{579d,579d,579d}	,{580d,580d,580d}	,{581d,581d,581d}	,{582d,582d,582d}	,
		{583d,583d,583d}	,{584d,584d,584d}	,{585d,585d,585d}	,{586d,586d,586d}	,{587d,587d,587d}	,
		{588d,588d,588d}	,{589d,589d,589d}	,{590d,590d,590d}	,{591d,591d,591d}	,{592d,592d,592d}	,
		{593d,593d,593d}	,{594d,594d,594d}	,{595d,595d,595d}	,{596d,596d,596d}	,{597d,597d,597d}	,
		{598d,598d,598d}	,{599d,599d,599d}	,
		{600d,600d,600d}	,{601d,601d,601d}	,{602d,602d,602d}	,{603d,603d,603d}	,{604d,604d,604d}	,
		{605d,605d,605d}	,{606d,606d,606d}	,{607d,607d,607d}	,{608d,608d,608d}	,{609d,609d,609d}	,
		{610d,610d,610d}	,{611d,611d,611d}	,{612d,612d,612d}	,{613d,613d,613d}	,{614d,614d,614d}	,
		{615d,615d,615d}	,{616d,616d,616d}	,{617d,617d,617d}	,{618d,618d,618d}	,{619d,619d,619d}	,
		{620d,620d,620d}	,{621d,621d,621d}	,{622d,622d,622d}	,{623d,623d,623d}	,{624d,624d,624d}	,
		{625d,625d,625d}	,{626d,626d,626d}	,
		{627d,627d,627d}	,{628d,628d,628d}	,{629d,629d,629d}	,{630d,630d,630d}	,{631d,631d,631d}	,
		{632d,632d,632d}	,{633d,633d,633d}	,{634d,634d,634d}	,{635d,635d,635d}	,{636d,636d,636d}	,
		{637d,637d,637d}	,{638d,638d,638d}	,{639d,639d,639d}	,{640d,640d,640d}	,{641d,641d,641d}	,
		{642d,642d,642d}	,{643d,643d,643d}	,{644d,644d,644d}	,{645d,645d,645d}	,{646d,646d,646d}	,
		{647d,647d,647d}	,{648d,648d,648d}	,{649d,649d,649d}	,{650d,650d,650d}	,{651d,651d,651d}	,
		{652d,652d,652d}	,{653d,653d,653d}	,
		{654d,654d,654d}	,{655d,655d,655d}	,{656d,656d,656d}	,{657d,657d,657d}	,{658d,658d,658d}	,
		{659d,659d,659d}	,{660d,660d,660d}	,{661d,661d,661d}	,{662d,662d,662d}	,{663d,663d,663d}	,
		{664d,664d,664d}	,{665d,665d,665d}	,{666d,666d,666d}	,{667d,667d,667d}	,{668d,668d,668d}	,
		{669d,669d,669d}	,{670d,670d,670d}	,{671d,671d,671d}	,{672d,672d,672d}	,{673d,673d,673d}	,
		{674d,674d,674d}	,{675d,675d,675d}	,{676d,676d,676d}	,{677d,677d,677d}	,{678d,678d,678d}	,
		{679d,679d,679d}	,{680d,680d,680d}	,
		{681d,681d,681d}	,{682d,682d,682d}	,{683d,683d,683d}	,{684d,684d,684d}	,{685d,685d,685d}	,
		{686d,686d,686d}	,{687d,687d,687d}	,{688d,688d,688d}	,{689d,689d,689d}	,{690d,690d,690d}	,
		{691d,691d,691d}	,{692d,692d,692d}	,{693d,693d,693d}	,{694d,694d,694d}	,{695d,695d,695d}	,
		{696d,696d,696d}	,{697d,697d,697d}	,{698d,698d,698d}	,{699d,699d,699d}	,{700d,700d,700d}	,
		{701d,701d,701d}	,{702d,702d,702d}	,{703d,703d,703d}	,{704d,704d,704d}	,{705d,705d,705d}	,
		{706d,706d,706d}	,{707d,707d,707d}	,
		{708d,708d,708d}	,{709d,709d,709d}	,{710d,710d,710d}	,{711d,711d,711d}	,{712d,712d,712d}	,
		{713d,713d,713d}	,{714d,714d,714d}	,{715d,715d,715d}	,{716d,716d,716d}	,{717d,717d,717d}	,
		{718d,718d,718d}	,{719d,719d,719d}	,{720d,720d,720d}	,{721d,721d,721d}	,{722d,722d,722d}	,
		{723d,723d,723d}	,{724d,724d,724d}	,{725d,725d,725d}	,{726d,726d,726d}	,{727d,727d,727d}	,
		{728d,728d,728d}	,{729d,729d,729d}	,{730d,730d,730d}	,{731d,731d,731d}	,{732d,732d,732d}	,
		{733d,733d,733d}	,{734d,734d,734d}	,
		{735d,735d,735d}	,{736d,736d,736d}	,{737d,737d,737d}	,{738d,738d,738d}	,{739d,739d,739d}	,
		{740d,740d,740d}	,{741d,741d,741d}	,{742d,742d,742d}	,{743d,743d,743d}	,{744d,744d,744d}};

	public static final double[][] SAMPLE_DATA_3D_WEB = {	
		{9,26.0,2300}	,{0,26.5,2350}	,{8,27.1,2950}	,{3,25.0,2300}	,
		{4,29.3,3200}	,{0,25.8,2600}	,{5,26.1,2800}	,{6,27.7,2500}	,
		{6,27.4,2700}	,{2,30.2,3275}	,{6,24.5,1950}	,{0,28.0,2625}	,
		{8,28.3,3050}	,{0,23.8,2100}	,{0,23.7,1950}	,{0,25.8,2650}	,
		{11,28.2,3050}	,{14,26.0,2300}	,{1,25.2,2000}	,{1,29.0,3000}	,
		{5,27.4,2700}	,{4,23.2,1950}	,{1,22.5,1600}	,{3,28.7,3150}	,
		{5,26.8,2700}	,{0,24.9,2100}	,{0,25.7,2000}	,{8,25.7,2000}	,
		{5,26.7,2700}	,{0,26.8,2650}	,{6,27.5,3150}	,{6,27.9,2800}	,
		{5,30.0,3300}	,{6,28.2,2600}	,{4,25.0,2100}	,{3,28.5,3000}	,
		{3,30.3,3600}	,{5,27.7,2900}	,{4,22.9,1600}	,{5,25.7,2000}	,
		{15,28.3,3000}	,{3,27.2,2700}	,{0,27.8,2750}	,{5,26.0,2150}	,
		{1,28.0,2800}	,{8,30.0,3050}	,{10,29.0,3200}	,{0,26.2,2400}	,
		{0,26.5,1300}	,{3,26.2,2400}	,{6,25.4,2250}	,{0,22.9,1600}	,
		{4,25.4,2250}	,{5,25.1,2100}	,{0,25.8,2300}	,{0,28.5,3050}	,
		{0,24.0,1700}	,{5,29.7,3850}	,{0,26.8,2550}	,{0,28.7,3200}	,
		{1,29.0,2800}	,{1,28.2,2867}	,{1,24.5,1600}	,{1,27.5,2550}	,
		{4,24.7,2550}	,{1,25.2,2000}	,{1,26.3,2400}	,{1,29.0,3100}	,
		{2,25.3,1900}	,{4,26.5,2300}	,{3,27.8,3250}	,{6,27.0,2500}	,
		{2,25.0,2100}	,{2,31.9,3325}	,{5,25.0,2400}	,{2,24.2,1650}	,
		{3,27.4,2900}	,{0,25.4,2300}	,{2,26.2,2025}	,{6,24.9,2300}	,
		{0,25.1,1800}	,{4,28.0,2900}	,{7,27.9,3050}	,{0,24.9,2200}	,
		{5,28.4,3100}	,{6,25.0,2250}	,{6,27.5,2625}	,{7,33.5,5200}	,
		{3,30.5,3325}	,{0,24.3,2000}	,{0,25.8,2400}	,{4,31.7,3725}	,
		{4,29.5,3025}	,{9,30.0,3000}	,{4,27.6,2850}	,{0,26.2,2300}	,
		{0,23.1,2000}	,{0,22.9,1600}	,{4,24.7,1950}	,{0,28.3,3200}	,
		{2,23.9,1850}	,{4,26.5,2350}	,{3,26.0,2275}	,{8,28.2,3050}	,
		{7,26.5,2750}	,{0,25.8,2200}	,{0,24.5,2000}	,{0,22.5,1550}	,
		{0,24.8,2100}	,{4,26.0,2600}	,{0,24.7,1900}	,{0,25.6,2150}	,
		{0,24.3,2150}	,{2,26.7,2600}	,{3,27.5,3100}	,{9,28.5,3250}	,
		{4,28.9,2800}	,{3,26.2,2300}	,{0,27.1,2550}	,{5,24.5,2050}	,
		{3,27.0,2450}	,{7,25.6,2800}	,{1,23.0,1650}	,{0,23.0,1800}	,
		{0,24.2,1900}	,{3,26.0,2200}	,{0,25.7,1200}	,{0,24.5,2250}	,
		{0,23.1,1650}	,{4,25.9,2550}	,{0,23.1,1550}	,{0,25.5,2250}	,
		{1,26.5,1967}	,{1,24.5,2200}	,{1,28.5,3000}	,{1,27.3,2900}	,
		{0,25.7,2100}	,{0,22.0,1400}	,{6,27.0,2500}	,{6,23.8,1800}	,
		{0,26.2,2225}	,{3,28.4,3200}	,{5,27.2,2400}	,{3,29.0,2925}	,
		{10,24.0,1900}	,{0,23.8,1800}	,{4,29.8,3500}	,{0,24.1,1800}	,
		{2,26.2,2175}	,{3,26.1,2750}	,{4,29.0,3275}	,{0,21.0,1850}	,
		{0,24.7,2200}	,{3,25.8,2000}	,{0,26.2,1300}	,{0,27.5,2600}	,
		{0,23.7,1850}	,{0,23.4,1900}	,{5,24.7,2100}	,{0,25.5,2250}	,
		{0,27.5,2900}	,{3,27.0,2250}	,{0,25.5,2750}	,{0,23.5,1900}	,
		{0,26.7,2450}	,{0,23.7,1800}	,{12,29.3,3225}	,{4,22.5,1475}	,
		{10,25.8,2250}	,{8,25.0,2100}	,{0,24.5,1900}	,{0,25.7,2150}	,
		{0,27.0,2625}	};

	public static final double SAMPLE_DATA_5D_WEB[][] = {
		{2,1,26.5,0,2350}	,{2,1,27.1,8,2950}	,{2,2,25,3,2300}	,{2,1,29.3,4,3200}	,
		{2,3,25.8,0,2600}	,{2,1,26.1,5,2800}	,{2,1,27.7,6,2500}	,{2,1,27.4,6,2700}	,
		{2,1,30.2,2,3275}	,{2,2,24.5,6,1950}	,{2,1,28,0,2625}	,{3,3,28.3,8,3050}	,
		{3,3,23.8,0,2100}	,{3,1,23.7,0,1950}	,{3,3,25.8,0,2650}	,{3,3,28.2,11,3050}	,
		{3,1,26,14,2300}	,{3,3,25.2,1,2000}	,{3,3,29,1,3000}	,{3,3,27.4,5,2700}	,
		{3,2,23.2,4,1950}	,{3,1,22.5,1,1600}	,{3,3,28.7,3,3150}	,{3,1,26.8,5,2700}	,
		{3,3,24.9,0,2100}	,{3,2,25.7,0,2000}	,{3,1,25.7,8,2000}	,{3,1,26.7,5,2700}	,
		{3,3,26.8,0,2650}	,{3,3,27.5,6,3150}	,{3,3,27.9,6,2800}	,{3,1,30,5,3300}	,
		{3,3,28.2,6,2600}	,{3,3,25,4,2100}	,{3,3,28.5,3,3000}	,{3,1,30.3,3,3600}	,
		{3,3,27.7,5,2900}	,{3,3,22.9,4,1600}	,{3,1,25.7,5,2000}	,{3,3,28.3,15,3000}	,
		{3,3,27.2,3,2700}	,{3,1,27.8,0,2750}	,{3,3,26,5,2150}	,{3,3,28,1,2800}	,
		{3,3,30,8,3050}		,{3,3,29,10,3200}	,{3,3,26.2,0,2400}	,{3,1,26.5,0,1300}	,
		{3,3,26.2,3,2400}	,{3,3,25.4,6,2250}	,{3,2,22.9,0,1600}	,{3,3,25.4,4,2250}	,
		{3,3,25.1,5,2100}	,{3,3,25.8,0,2300}	,{3,3,28.5,0,3050}	,{3,2,24,0,1700}	,
		{3,1,29.7,5,3850}	,{3,1,26.8,0,2550}	,{3,1,28.7,0,3200}	,{3,1,29,1,2800}	,
		{3,3,28.2,1,2867}	,{3,3,24.5,1,1600}	,{3,3,27.5,1,2550}	,{3,2,24.7,4,2550}	,
		{3,1,25.2,1,2000}	,{3,3,26.3,1,2400}	,{3,3,29,1,3100}	,{3,3,25.3,2,1900}	,
		{3,3,26.5,4,2300}	,{3,3,27.8,3,3250}	,{3,3,27,6,2500}	,{3,3,25,2,2100}	,
		{3,3,31.9,2,3325}	,{3,3,25,5,2400}	,{3,3,24.2,2,1650}	,{3,3,27.4,3,2900}	,
		{3,2,25.4,0,2300}	,{3,3,26.2,2,2025}	,{3,1,24.9,6,2300}	,{3,3,25.1,0,1800}	,
		{3,1,28,4,2900}		,{3,3,27.9,7,3050}	,{3,3,24.9,0,2200}	,{3,1,28.4,5,3100}	,
		{3,2,25,6,2250}		,{3,3,27.5,6,2625}	,{3,1,33.5,7,5200}	,{3,3,30.5,3,3325}	,
		{3,1,24.3,0,2000}	,{3,3,25.8,0,2400}	,{3,1,31.7,4,3725}	,{3,3,29.5,4,3025}	,
		{3,3,30,9,3000}		,{3,3,27.6,4,2850}	,{3,3,26.2,0,2300}	,{3,1,23.1,0,2000}	,
		{3,1,22.9,0,1600}	,{3,3,24.7,4,1950}	,{3,3,28.3,0,3200}	,{3,3,23.9,2,1850}	,
		{3,3,26.5,4,2350}	,{3,3,26,3,2275}	,{3,3,28.2,8,3050}	,{3,3,26.5,7,2750}	,
		{3,3,25.8,0,2200}	,{3,2,24.5,0,2000}	,{4,3,22.5,0,1550}	,{4,3,24.8,0,2100}	,
		{4,3,26,4,2600}		,{4,2,24.7,0,1900}	,{4,3,25.6,0,2150}	,{4,3,24.3,0,2150}	,
		{4,3,26.7,2,2600}	,{4,3,27.5,3,3100}	,{4,1,28.5,9,3250}	,{4,3,28.9,4,2800}	,
		{4,3,26.2,3,2300}	,{4,3,27.1,0,2550}	,{4,3,24.5,5,2050}	,{4,1,27,3,2450}	,
		{4,3,25.6,7,2800}	,{4,3,23,1,1650}	,{4,3,23,0,1800}	,{4,3,24.2,0,1900}	,
		{4,2,26,3,2200}		,{4,3,25.7,0,1200}	,{4,2,24.5,0,2250}	,{4,3,23.1,0,1650}	,
		{4,1,25.9,4,2550}	,{4,3,23.1,0,1550}	,{4,3,25.5,0,2250}	,{4,3,26.5,1,1967}	,
		{4,3,24.5,1,2200}	,{4,3,28.5,1,3000}	,{4,3,27.3,1,2900}	,{4,3,25.7,0,2100}	,
		{4,3,22,0,1400}		,{4,3,27,6,2500}	,{4,3,23.8,6,1800}	,{4,3,26.2,0,2225}	,
		{4,3,28.4,3,3200}	,{4,3,27.2,5,2400}	,{4,3,29,3,2925}	,{4,3,24,10,1900}	,
		{4,3,23.8,0,1800}	,{4,2,29.8,4,3500}	,{4,3,24.1,0,1800}	,{4,3,26.2,2,2175}	,
		{4,3,26.1,3,2750}	,{4,3,29,4,3275}	,{5,2,21,0,1850}	,{5,3,24.7,0,2200}	,
		{5,3,25.8,3,2000}	,{5,3,26.2,0,1300}	,{5,3,27.5,0,2600}	,{5,3,23.7,0,1850}	,
		{5,3,23.4,0,1900}	,{5,3,24.7,5,2100}	,{5,3,25.5,0,2250}	,{5,3,27.5,0,2900}	,
		{5,3,27,3,2250}		,{5,1,25.5,0,2750}	,{5,3,23.5,0,1900}	,{5,3,26.7,0,2450}	,
		{5,3,23.7,0,1800}	,{5,3,29.3,12,3225}	,{5,3,22.5,4,1475}	,{5,3,25.8,10,2250}	,
		{5,3,25,8,2100}		,{5,3,24.5,0,1900}	,{5,3,25.7,0,2150}	,{5,3,27,0,2625}	};

	public static ArrayList<double[]> getData( double[][] input ) {
		ArrayList<double[]> result = new ArrayList<double[]>();

		for ( double[] i : input )
			result.add(i);

		return result;
	}
}