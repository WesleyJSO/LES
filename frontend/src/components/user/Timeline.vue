 <template>
  <div>
      <Appoint></Appoint>
    <h1>Últimos Apontamentos</h1>
    <br/>
    <v-spacer></v-spacer>
    <h2>Hoje</h2>
    <section id="cd-timeline" class="cd-container">
      <div class="cd-timeline-block">
        <div class="cd-timeline-img cd-picture">
          <img src="@/assets/logo.png" alt="Buger">
        </div>
        <div class="cd-timeline-content elevation-7">
          <h3>
            <v-avatar>
              <img src="@/assets/burger.png" alt="Burger">
            </v-avatar>
            Lanche do Seven King's Burger hoje!
          </h3>
          <div class="headline text-xs-left">
            <p><b>Saída:</b> 12:00 <br/> <b>Retorno:</b> 13:00</p>
            <p> a</p>
          </div>
          <div></div>
            <v-bottom-nav :active.sync="like"
              :value="true"
              absolute
              color="trasnparent">
              <v-btn color="black" flat value="like">
                <span>Compartilhar Horas</span>
                  <v-icon>thumb_up</v-icon>
              </v-btn>
              <v-btn color="black" flat value="favorites">
                <span>Favoritar</span>
                <v-icon>favorite</v-icon>
              </v-btn>
            </v-bottom-nav>
          <span class="cd-date"><b>José Victor Zeller</b> - 08/09/2018 13:10</span>
        </div>
      </div>
      <div class="cd-timeline-block">
        <div class="cd-timeline-img cd-movie">
          <img src="@/assets/logo.png" alt="Vue.js logo">
        </div>
        <div class="cd-timeline-content elevation-7">
          <h3>
            <v-avatar>
              <img src="@/assets/food.jpeg" alt="Food">
            </v-avatar>
            Almoço no Restaurante X!
          </h3>
          <div class="headline text-xs-left">
            <p><b>Saída:</b> 12:30 <br/> <b>Retorno:</b> 13:10</p>
            <p> a</p>
          </div>
          <div></div>
            <v-bottom-nav :active.sync="like"
              :value="true"
              absolute
              color="trasnparent">
              <v-btn color="black" flat value="like">
                <span>Compartilhar Horas</span>
                  <v-icon>thumb_up</v-icon>
              </v-btn>
              <v-btn color="black" flat value="favorites">
                <span>Favoritar</span>
                <v-icon>favorite</v-icon>
              </v-btn>
            </v-bottom-nav>
          <span class="cd-date"><b>Bruno Holanda</b> - 08/09/2018 13:15</span>
        </div>
      </div>
      <div class="cd-timeline-block">
        <div class="cd-timeline-img cd-picture">
          <img src="@/assets/logo.png" alt="Vue.js logo">
        </div>
        <div class="cd-timeline-content elevation-7">
          <h3>
            <v-avatar>
              <img src="@/assets/cafe.png" alt="Cafe">
            </v-avatar>
            Café na Padaria Dona Benta!
          </h3>
          <div class="headline text-xs-left">
            <p><b>Saída:</b> 09:40 <br/> <b>Retorno:</b> 10:20</p>
            <p> a</p>
          </div>
          <div></div>
            <v-bottom-nav :active.sync="like"
              :value="true"
              absolute
              color="trasnparent">
              <v-btn color="black" flat value="like">
                <span>Compartilhar Horas</span>
                  <v-icon>thumb_up</v-icon>
              </v-btn>
              <v-btn color="black" flat value="favorites">
                <span>Favoritar</span>
                <v-icon>favorite</v-icon>
              </v-btn>
            </v-bottom-nav>
          <span class="cd-date"><b>Wesley Silva</b> - 08/09/2018 10:25</span>
        </div>
      </div>
    </section>
</div>
 </template>
 <script>
import Appoint from '@/components/user/Appoint'

export default {
  data: () => ({
    timelineData: [{
      // TODO: Implement data bind and events
    }]
  }),
  components: {
    Appoint
  },
  methods: {
  }
}
</script>
<style scoped>
  html * {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

*, *:after, *:before {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  font-size: 100%;
  color: #424242;
  background-color: #424242;
}
img {
  max-width: 100%;
}
h2 {
  font-size: 20px;
  text-align: center;
  color: #424242;
}
.cd-container {
  /* this class is used to give a max-width to the element it is applied to, and center it horizontally when it reaches that max-width */
  width: 90%;
  max-width: 1200px;
  /* Another CSS hack learned from Luque's class =) */
  margin: 0 auto 0 auto;
}
.cd-container::after {
  /* Luque's clearfix hack here =) */
  content: '';
  display: table;
  clear: both;
}
/* Main components's style */
#cd-timeline {
  position: relative;
  padding: 2em 0;
  margin-top: 2em;
  margin-bottom: 2em;
}
#cd-timeline::before {
  /* Vertical line */
  content: '';
  position: absolute;
  top: 0;
  left: 18px;
  height: 100%;
  width: 4px;
  background: #424242;
}
/* Make timeline resposive */
@media only screen and (min-width: 1200px) {
  #cd-timeline {
    margin-top: 3em;
    margin-bottom: 3em;
  }
  #cd-timeline::before {
    left: 50%;
    margin-left: -2px;
  }
}

.cd-timeline-block {
  position: relative;
  margin: 2em 0;
}
.cd-timeline-block:after {
  content: "";
  display: table;
  clear: both;
}
.cd-timeline-block:first-child {
  margin-top: 0;
}
.cd-timeline-block:last-child {
  margin-bottom: 0;
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-block {
    margin: 4em 0;
  }
  .cd-timeline-block:first-child {
    margin-top: 0;
  }
  .cd-timeline-block:last-child {
    margin-bottom: 0;
  }
}

.cd-timeline-img {
  position: absolute;
  top: 0;
  left: 0;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  box-shadow: 0 0 0 4px white, inset 0 2px 0 rgba(0, 0, 0, 0.08), 0 3px 0 4px rgba(0, 0, 0, 0.05);
}
.cd-timeline-img img {
  display: block;
  width: 24px;
  height: 24px;
  position: relative;
  left: 50%;
  top: 50%;
  margin-left: -12px;
  margin-top: -12px;
}
.cd-timeline-img.cd-picture {
  background: #75ce66;
}
.cd-timeline-img.cd-movie {
  background: #c03b44;
}
.cd-timeline-img.cd-location {
  background: #f0ca45;
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-img {
    width: 60px;
    height: 60px;
    left: 50%;
    margin-left: -30px;
    /* Force Hardware Acceleration in WebKit and other Webkit stuffs*/
    -webkit-transform: translateZ(0);
    -webkit-backface-visibility: hidden;
  }
  .cssanimations .cd-timeline-img.is-hidden {
    visibility: hidden;
  }
  .cssanimations .cd-timeline-img.bounce-in {
    visibility: visible;
    -webkit-animation: cd-bounce-1 0.6s;
    -moz-animation: cd-bounce-1 0.6s;
    animation: cd-bounce-1 0.6s;
  }
}

.cd-timeline-content {
  position: relative;
  margin-left: 60px;
  background: white;
  border-radius: 0.25em;
  padding: 1em;
  box-shadow: 0 3px 0 #d7e4ed;
}
.cd-timeline-content:after {
  content: "";
  display: table;
  clear: both;
}
.cd-timeline-content h2 {
  color: #303e49;
}
.cd-timeline-content p, .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
  font-size: 13px;
  font-size: 0.8125rem;
}
.cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
  display: inline-block;
}
.cd-timeline-content p {
  margin: 1em 0;
  line-height: 1.6;
}
.cd-timeline-content .cd-read-more {
  float: right;
  padding: .8em 1em;
  background: #acb7c0;
  color: white;
  border-radius: 0.25em;
}
.no-touch .cd-timeline-content .cd-read-more:hover {
  background-color: #bac4cb;
}
a.cd-read-more:hover{text-decoration:none; background-color: #424242;  }
.cd-timeline-content .cd-date {
  float: left;
  padding: .8em 0;
  opacity: .7;
}
.cd-timeline-content::before {
  content: '';
  position: absolute;
  top: 16px;
  right: 100%;
  height: 0;
  width: 0;
  border: 7px solid transparent;
  border-right: 7px solid white;
}
@media only screen and (min-width: 768px) {
  .cd-timeline-content h2 {
    font-size: 20px;
    font-size: 1.25rem;
  }
  .cd-timeline-content p {
    font-size: 16px;
    font-size: 1rem;
  }
  .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
    font-size: 14px;
    font-size: 0.875rem;
  }
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-content {
    margin-left: 0;
    padding: 1.6em;
    width: 45%;
  }
  .cd-timeline-content::before {
    top: 24px;
    left: 100%;
    border-color: transparent;
    border-left-color: white;
  }
  .cd-timeline-content .cd-read-more {
    float: left;
  }
  .cd-timeline-content .cd-date {
    position: absolute;
    width: 100%;
    left: 122%;
    top: 6px;
    font-size: 16px;
    font-size: 1rem;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content {
    float: right;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content::before {
    top: 24px;
    left: auto;
    right: 100%;
    border-color: transparent;
    border-right-color: white;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content .cd-read-more {
    float: right;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content .cd-date {
    left: auto;
    right: 122%;
    text-align: right;
  }
  .cssanimations .cd-timeline-content.is-hidden {
    visibility: hidden;
  }
  .cssanimations .cd-timeline-content.bounce-in {
    visibility: visible;
    -webkit-animation: cd-bounce-2 0.6s;
    -moz-animation: cd-bounce-2 0.6s;
    animation: cd-bounce-2 0.6s;
  }
}

@media only screen and (min-width: 1170px) {
  /* inverse bounce effect on even content blocks */
  .cssanimations .cd-timeline-block:nth-child(even) .cd-timeline-content.bounce-in {
    -webkit-animation: cd-bounce-2-inverse 0.6s;
    -moz-animation: cd-bounce-2-inverse 0.6s;
    animation: cd-bounce-2-inverse 0.6s;
  }
}

.form-container {
  margin-bottom: -100px;
}
</style>
