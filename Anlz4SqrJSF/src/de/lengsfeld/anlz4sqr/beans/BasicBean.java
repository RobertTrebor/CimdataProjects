package de.lengsfeld.anlz4sqr.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.lengsfeld.anlz4sqr.connect.FSManager;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

@ManagedBean
@SessionScoped
public class BasicBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Zugriff auf ein anderes Bean
	@ManagedProperty(value = "#{mapBean}")
	private MapBean mapBean;

	public void setMapBean(MapBean mapBean) {
		this.mapBean = mapBean;
	}

	@ManagedProperty(value = "#{categoriesController}")
	private CategoriesController categoriesController;
	
	public void setCategoriesController(CategoriesController categoriesController) {
		this.categoriesController = categoriesController;
	}

	private Result<VenuesSearchResult> result = null;
	private List<CompactVenue> venues = null;
	private CompactVenue selectedVenue = null;

	private String coordinates;
	private String query = null;
	private String category = null;

	public BasicBean() {
		System.setProperty("java.net.useSystemProxies", "true");
	}

	public void loadResult() {
		category = categoriesController.getCategoryId();
		if (category == "0000"){
			category = "";
		}
		System.out.println(category);
		coordinates = mapBean.getCoordinates();
		System.out.println(coordinates);
		try {
			result = new FSManager().draw3(coordinates, query,
					category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResult(result);
		venues = Arrays.asList(result.getResult().getVenues());
		setVenues(venues);
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Result<VenuesSearchResult> getResult() {
		return result;
	}

	public void setResult(Result<VenuesSearchResult> result) {
		this.result = result;
	}

	public void loadVenues() {
		venues = Arrays.asList(result.getResult().getVenues());
		setVenues(venues);
	}

	public List<CompactVenue> getVenues() {
		return venues;
	}

	public void setVenues(List<CompactVenue> venues) {
		this.venues = venues;
	}

	public CompactVenue getSelectedVenue() {
		if(selectedVenue != null) {
		System.out.println(selectedVenue.getName());
		System.out.println(selectedVenue.getLocation().getCity());
		System.out.println(selectedVenue.getLocation().getCountry());
		System.out.println(selectedVenue.getLocation().getCrossStreet());
		selectedVenue.getHereNow().getCount();
		System.out.println();
		}
		return selectedVenue;
	}

	public void setSelectedVenue(CompactVenue selectedVenue) {
		this.selectedVenue = selectedVenue;
	}
}