package com.productivity.testwind2.Screens.Web

import com.productivity.testwind2.Imports.Utils.Log.*
import com.productivity.testwind2.Imports.Utils.NavControl.*
import com.productivity.testwind2.Imports.Utils.String.*
import com.productivity.testwind2.*
import com.productivity.testwind2.Imports.Utils.Browser.*


fun BlockingLogic(web: WebController){
	fun Block(){
		goTo("WebHome")
		Bar.Url = "google.com"
	}
	fun youtubeFilter(){
		web.web?.youtubeFilter(
			allowOnly = listOf(
				"discipline",
				"my first million",
				"mindset",
				"AllThingsSecured",
				"university",
				"hard work",
				"motivation",
				"Mel Robbins",
				"Tony Robbins",
				"David Goggins",
				"Darren Hardy",
				"shareholder meeting",
				"shareholder annual meeting",
				"personal growth",
				"self improvement",
				"success mindset",
				"entrepreneurship",
				"business growth",
				"wealth building",
				"financial freedom",
				"investing",
				"stock market",
				"shareholder",
				"annual report",
				"company earnings",
				"business strategy",
				"leadership",
				"CEO mindset",
				"founder mindset",
				"startup",
				"startup growth",
				"building a company",
				"innovation",
				"execution",
				"time management",
				"goal setting",
				"daily habits",
				"morning routine",
				"consistency",
				"focus",
				"determination",
				"resilience",
				"confidence",
				"self belief",
				"mental toughness",
				"winning mindset",
				"growth mindset",
				"high performance",
				"peak performance",
				"success habits",
				"wealth mindset",
				"millionaire mindset",
				"billionaire mindset",
				"Robert Kiyosaki",
				"Jim Rohn",
				"Brian Tracy",
				"Simon Sinek",
				"Gary Vaynerchuk",
				"Alex Hormozi",
				"Naval Ravikant",
				"Warren Buffett",
				"Charlie Munger",
				"Elon Musk",
				"Jeff Bezos",
				"Steve Jobs",
				"business lessons",
				"life lessons",
				"career growth",
				"professional development",
				"leadership skills",
				"communication skills",
				"customer obsession",
				"company culture",
				"corporate strategy",
				"investment strategy",
				"long term thinking",
				"compound growth",
				"financial literacy",
				"money management",
				"passive income",
				"side hustle",
				"online business",
				"digital business",
				"creator economy",
				"content creation",
				"public speaking",
				"confidence building",
				"overcoming fear",
				"failure lessons",
				"success stories",
				"founderspodcast1",
				"work ethic",
				"inspiration",
				"motivational speech",
				"motivational podcast",
				"podcast",
				"self development podcast"
			),
			block = listOf("MrBeast", "McYum", "Mark Rober", " TABS ", " Aliens ", "Technoblade", "Skeppy", "Grian", "Spifey", "Minecraft", "Speedrun", "Mr Bean", "POV", "m views", "redstone", "command blocks", "00 IQ", "poly bridge", "iswho", "rageplaysgames", "leowook", "shalz", "chess", " vs ", " vs. ", "reddoons", "real civil engineer", "cappy army", "iwantcheckmate", "Can I", "but everyone", " noob ", " OP ", " 1v1 ", " 3v1 ", " 2v1 ", ".io", "! ", " trackmania ", " 100% ", "Players", "Market crash", "king", "flood", "but all", "geometry dash", "just lost", "world box", "law by mike", "smartest", "baronVonGames", "...", " SMP ", "1000 days", "100 days")         
		)
	}
	web.onPageFinished{
		youtubeFilter()
	}

	
	
	web.doUpdateVisitedHistory { url, isReload ->
		Bar.Url = url ?: "https://www.google.com"

		

		Bar.Url.blog("BarUrl")
		web.blockImages({Bar.Url.hasAny("youtube.com")})

		if (Bar.Url.hasAny(
			"melrobbins.com/podcast/", 
			"mfmpod.com", 
			"youtube.com",
		)) {
			if (Bar.Url.hasAny("/shorts/")) Block()
			//Allow
		} else {
			Block()
		}
	}
	
	
	
	


	/*

	web.doUpdateVisitedHistory { url, isReload ->
		Bar.Url = url ?: "https://www.google.com"

		if (WebUtils.HasBadWord(Bar.Url, { it.locked })) Block()
			
		if (WebUtils.HasBadWord(Bar.Url)){
			if (!WebUtils.HasGoodWord(Bar.Url)){
				Block()
			}
		}
	}
	
	web.shouldInterceptRequest {
		if (it.image) return@shouldInterceptRequest null
		
		var x = WebUtils.IsGood(it)
		if (x == WebAction.Allow) return@shouldInterceptRequest null
		if (x == WebAction.Block) Block()
		if (x == WebAction.Blot) return@shouldInterceptRequest EmptyWebResource()
		
		//: WebAction



		
		return@shouldInterceptRequest null
	}
	*/
}













