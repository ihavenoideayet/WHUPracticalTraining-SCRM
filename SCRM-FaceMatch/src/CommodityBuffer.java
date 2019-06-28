
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommodityBuffer {
	
	private static ConcurrentHashMap<Long,Integer> Commodities=new ConcurrentHashMap<Long,Integer>();
	
	public Map<Long,Integer> getCommodities()
	{	
		return new HashMap<Long, Integer>(Commodities);	
	}
	
	public static void addCommodity(long commodityID)
	{
		if(Scanner.flag == false) {
			Scanner.flag = true;
		}
		if(!Commodities.containsKey(commodityID))
		{
			Commodities.put(commodityID, 1);
		}
		else
		{
			int oldNum=Commodities.get(commodityID);
			Commodities.replace(commodityID, oldNum+1);
		}
	}
	
	public static void removeCommodity(long commodityID)
	{
		if(Commodities.containsKey(commodityID))
		{
			Commodities.remove(commodityID);
		}
	}
	
	public static void changeCommodityNum(long commodityID,int num) 
	{
		if(Commodities.containsKey(commodityID))
		{
			Commodities.replace(commodityID, num);
		}	
	}
	
	public static void ClearTheCommodities()
	{
		Scanner.flag = false;
		Commodities.clear();
	}
		
}
