package com.codingexercise.restapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codingexercise.restapi.client.MatchClient;
import com.codingexercise.restapi.model.Match;
import com.codingexercise.restapi.service.MatchService;

@SpringBootTest
class RestapiApplicationTests {

	@Autowired
	private MatchService matchService;

	@BeforeEach
	void setUp() {
		MatchClient testClient = new MatchClient() {
			@Override
			public List<Match> fetchAllMatches() {
				return List.of(
					new Match("Sacramento Kings", "Oklahoma City Thunder", "COMPLETED", Instant.now(), 113L, 103L),
					new Match("Portland Trail Blazers", "Milwaukee Bucks", "COMPLETED", Instant.now(), 113L, 103L),
					new Match("Denver Nuggets", "Brooklyn Nets", "LIVE", Instant.now(), 113L, 103L)
				);
			}
		};

		matchService = new MatchService(testClient);
	}

	@Test
	public void getAllMatchesTest() {
		List<Match> allMathes = matchService.getAllMatches();
		assertEquals(3, allMathes.size());
	}

	@Test
	public void getCompletedMatchesTest() {
		List<Match> completedMatches = matchService.getMatchesByStatus("COMPLETED");
		assertEquals(2, completedMatches.size());
		assertTrue(completedMatches.stream().allMatch(match -> match.getStatus().equals("COMPLETED")));
	}

	@Test
	public void getLiveMatchesTest() {
		List<Match> completedMatches = matchService.getMatchesByStatus("LIVE");
		assertEquals(1, completedMatches.size());
		assertTrue(completedMatches.stream().allMatch(match -> match.getStatus().equals("LIVE")));
	}

	@Test
	public void filterMatchesByTeamNameTest() {
		List<Match> filteredMatches = matchService.getMatchesByTeam("Sacramento Kings");
		assertEquals(1, filteredMatches.size());
		assertTrue(filteredMatches.stream().allMatch(match -> match.getHomeTeam().equals("Sacramento Kings")));
	}

	@Test
	public void filterMatchesByEmptyTeamNameTest() {
		List<Match> filteredMatches = matchService.getMatchesByTeam("");
		assertEquals(3, filteredMatches.size());
	}

}
