


package nationGen.naming;

import java.util.ArrayList;
import java.util.List;

import nationGen.units.Unit;

import com.elmokki.Generic;


public class Name  {
	
	public NamePart type;
	public NamePart rankprefix;
	public NamePart prefix;
	public NamePart prefixprefix;
	public NamePart suffixprefix;
	public NamePart suffix;
	
	
	public void setType(String str)
	{

		this.type = NamePart.newNamePart(str, null);
	}
	public void setRankPrefix(String str)
	{
		this.rankprefix = NamePart.newNamePart(str, null);
	}
	public void setPrefix(String str)
	{
		this.prefix = NamePart.newNamePart(str, null);
	}
	public void setPrefixprefix(String str)
	{
		this.prefixprefix = NamePart.newNamePart(str, null);
	}
	public void setSuffixprefix(String str)
	{
		this.suffixprefix = NamePart.newNamePart(str, null);
	}
	public void setSuffix(String str)
	{
		this.suffix = NamePart.newNamePart(str, null);
	}
	
	

	public boolean pluralsuffix = false;
	public boolean definitesuffix = false;
	
	public Name()
	{
		String temp = new String();
		temp = "UNNAMED";
		this.setType(temp);
	}
	
	public Name getCopy()
	{
		Name name = new Name();
		name.prefixprefix = prefixprefix;
		name.prefix = prefix;
		name.type = type;
		name.suffixprefix = suffixprefix;
		name.suffix = suffix;
		name.pluralsuffix = pluralsuffix;
		name.definitesuffix = definitesuffix;
		return (Name)name;
	}
	
	

	public String toString(Unit u)
	{
		String str = "";
		
		if(rankprefix != null)
			str = str + rankprefix.toString(u) + " ";
		
		if(prefixprefix != null)
			str = str + prefixprefix.toString(u) + " ";
		if(prefix != null)
			str = str + prefix.toString(u) + " ";
		if(type != null)
			str = str + type.toString(u) + " ";
		
		if(suffixprefix != null || suffix != null)
			str = str + "of ";
		
		if(definitesuffix && (suffix != null || suffixprefix != null))
			str = str + "the ";

		if(suffixprefix != null)
			str = str + suffixprefix.toString(u) + " ";
		if(suffix != null && !pluralsuffix)
			str = str + suffix.toString(u) + " ";
		else if(suffix != null)
			str = str + Generic.plural(suffix.toString(u)) + " ";

		if(str.endsWith(" "))
			str = str.substring(0, str.length() - 1);
		
		

		return NameGenerator.capitalize(str);
	}
	
	public String toString()
	{
		return this.toString(null);
	}
	
	public String getPrefixSuffix()
	{
		String str = "";
		if(prefixprefix != null)
			str = str + prefixprefix + " ";
		if(prefix != null)
			str = str + prefix + " ";
		
		if(suffixprefix != null)
			str = str + suffixprefix + " ";
		if(suffix != null)
			str = str + suffix + " ";
		
		if(str.endsWith(" "))
			str = str.substring(0, str.length() - 1);
		
		return str;
	}
	
	public List<String> getAsList()
	{
		List<String> list = new ArrayList<String>();
		list.add(prefixprefix.name);
		list.add(prefix.name);
		list.add(type.name);
		list.add(suffixprefix.name);
		list.add(suffix.name);
		return list;
	}
	

	
	public boolean isDuplicate(Name other)
	{
		List<String> otherList = other.getAsList();
		List<String> thisList = this.getAsList();
		
		boolean areSame = true;
		
		for(int i = 0; i < otherList.size(); i++)
		{
			String thisPart = thisList.get(i);
			String otherPart = otherList.get(i);
			
			if(thisPart != null)
			{
				if(otherPart == null) // Both need to be null if one is
					areSame = false;
				else if(!otherPart.equals(thisPart)) // Different text
					areSame = false;
			}
			else if(otherPart != null) // Both need to be null if one is
				areSame = false;
		}
		
		return areSame;
			
	}

}






