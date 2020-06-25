import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  recipe: any;
  place: any;

  placeList = [];
  recipeList = [];

  geolocationPosition: any;
  currentLat: any;
  currentLng: any;

  app_id_edamam = "f658ce12";
  app_key_edamam = "360eeb5e1e1e937a7565515ae2c481c0";

  app_id_fs ="ZGTUAH02GPCMIMLK1NLNBHHIBTUIU13NRTI33WY3G025WOGB";
  app_secret_fs ="IOAJPXPAJYRA0131WTSBLQGPJNGE4BJVFBB5J0IFSSCMKUWR";

  constructor(private GetHttp: HttpClient) { }

  ngOnInit() {
    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLng = position.coords.longitude;
      });
  }

  getNutrition() {
    if (this.recipe !== null) {
      this.GetHttp.get('https://api.edamam.com/search?q=' +
        this.recipe +
        '&app_id='+this.app_id_edamam+'&app_key='+this.app_key_edamam+'&from=0&to=5')
        .subscribe((data: any) => {
          for (let i = 0; i < data.hits.length; i++) {
            this.recipeList[i] = {
              name: data.hits[i].recipe.label,
              shareAs: data.hits[i].recipe.shareAs,
              calories: data.hits[i].recipe.calories,
              icon: data.hits[i].recipe.image,
              Protein: data.hits[i].recipe.totalNutrients.PROCNT.quantity,
              fat: data.hits[i].recipe.totalNutrients.FAT.quantity,
              carbs: data.hits[i].recipe.totalNutrients.CHOCDF.quantity
            };
          }
        });
    }

    // https://developer.foursquare.com/docs/api/venues/search

    if (this.place !== null && this.place !== '' && this.recipe != null && this.recipe !== '') {
      this.GetHttp.get('https://api.foursquare.com/v2/venues/search' +
        '?client_id=' + this.app_id_fs +
        '&client_secret=' + this.app_secret_fs +
        '&v=20160215&limit=10' +
        '&near=' + this.place +
        '&query=' + this.recipe)
        .subscribe((data: any) => {
          for (let i = 0; i < data.response.venues.length; i++) {
            this.placeList[i] = {
              name: data.response.venues[i].name,
              address: data.response.venues[i].location.address,
              location: data.response.venues[i].location,
            };
          }

        });
    }
  }
}
