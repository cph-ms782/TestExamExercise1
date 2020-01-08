(this.webpackJsonpreact_security=this.webpackJsonpreact_security||[]).push([[0],{23:function(e,t,n){e.exports=n(37)},28:function(e,t,n){},37:function(e,t,n){"use strict";n.r(t);var l=n(0),a=n.n(l),r=n(16),o=n.n(r),i=(n(28),n(6)),c=n(9),s=n(5),u=n(17),m=n(18),g="https://sandersolutions.dk/testexamexercise1";function d(e){return e.ok?e.json():Promise.reject({status:e.status,fullError:e.json()})}var p=new(function(){function e(){var t=this;Object(u.a)(this,e),this.setToken=function(e){localStorage.setItem("jwtToken",e)},this.getToken=function(){return localStorage.getItem("jwtToken")},this.loggedIn=function(){return console.log("loggedIn func"),null!=t.getToken()},this.logout=function(){localStorage.removeItem("jwtToken")},this.login=function(e,n){console.log("user, pass",e,n);var l=t.makeOptions("POST",!0,{username:e,password:n});return console.log("URL",g),console.log("options",l),fetch(g+"/api/login",l).then(d).then((function(e){t.setToken(e.token)})).catch((function(e){throw e}))},this.getRole=function(){var e=localStorage.getItem("jwtToken").split(".")[1],t=window.atob(e);return JSON.parse(t).roles},this.fetchSW=function(){console.log("fetchSW");var e=t.makeOptions("GET",!0);return fetch(g+"/api/sw/datadto",e).then(d)},this.fetchData=function(){console.log("fetchData");var e=t.makeOptions("GET",!0);return"admin"===t.getRole()?fetch(g+"/api/info/admin",e).then(d):fetch(g+"/api/info/user",e).then(d)}}return Object(m.a)(e,[{key:"makeOptions",value:function(e,t,n){console.log("makeOptions");var l={method:e,headers:{"Content-type":"application/json",Accept:"application/json"}};return t&&this.loggedIn()&&(l.headers["x-access-token"]=this.getToken()),n&&(l.body=JSON.stringify(n)),console.log("opts",l),l}}]),e}());var E=function(e){console.log("LogIn");var t=Object(l.useState)(""),n=Object(i.a)(t,2),r=n[0],o=n[1],c=Object(l.useState)(""),s=Object(i.a)(c,2),u=s[0],m=s[1];return console.log("user, pass",r,u),e.loggedIn?a.a.createElement("div",null,a.a.createElement("h2",null,"Logout"),a.a.createElement("button",{onClick:function(t){console.log("LogIn - logout"),t.preventDefault(),e.logout()}},"Logout")):a.a.createElement("div",null,a.a.createElement("h2",null,"Login"),a.a.createElement("form",{onChange:function(e){console.log("onChange  ->",e.target.id),"username"==[e.target.id]?(console.log("username",e.target.value),o(e.target.value)):(console.log("password",e.target.value),m(e.target.value))}},a.a.createElement("input",{placeholder:"User Name",id:"username"}),a.a.createElement("input",{placeholder:"Password",id:"password"}),a.a.createElement("button",{onClick:function(t){console.log("LogIn - login"),t.preventDefault(),console.log("user, pass",r,u),e.login(r,u)}},"Login")))},h=n(11),f=n.n(h),v=n(19),k=n(20),b=n.n(k);var j=function(e){var t=e.loggedIn;console.log("Data"),console.log("loggedIn",t);var n=Object(l.useState)([]),r=Object(i.a)(n,2),o=r[0],c=r[1];return Object(l.useEffect)((function(){t&&function(){var e=Object(v.a)(f.a.mark((function e(){var t;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,p.fetchSW();case 3:t=e.sent,console.log("data",t),c(t),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log("err",e.t0);case 11:case"end":return e.stop()}}),e,null,[[0,8]])})));return function(){return e.apply(this,arguments)}}()()}),[]),console.log("starwars",o),t?a.a.createElement("div",null,a.a.createElement("table",null,a.a.createElement("thead",null,a.a.createElement("tr",null,a.a.createElement("td",null,a.a.createElement("b",null,"Name")),a.a.createElement("td",null,a.a.createElement("b",null,"URL")))),a.a.createElement("tbody",null,o.map((function(e){return a.a.createElement("tr",{key:b()()},a.a.createElement("td",null,e.name),a.a.createElement("td",null,e.url))}))))):a.a.createElement("div",null,a.a.createElement("h2",null," Please login"))};var I=function(){return console.log("Home"),a.a.createElement("div",null,a.a.createElement("p",null,"Master",a.a.createElement("br",null),a.a.createElement("a",{href:"https://travis-ci.org/cph-ms782/Course-Assignment-3_Backend"},a.a.createElement("img",{alt:"Build Status",src:"https://travis-ci.org/cph-ms782/Course-Assignment-3_Backend.svg?branch=master"})),"  "),a.a.createElement("p",null,"Dev",a.a.createElement("br",null),a.a.createElement("a",{href:"https://travis-ci.org/cph-ms782/Course-Assignment-3_Backend"},a.a.createElement("img",{alt:"Build Status",src:"https://travis-ci.org/cph-ms782/Course-Assignment-3_Backend.svg?branch=dev"})),"  "),a.a.createElement("h1",null,"Course-Assignment-3"),a.a.createElement("h3",null,"endpoints"),a.a.createElement("p",null,a.a.createElement("strong",null,"All"),"  "),a.a.createElement("ul",null,a.a.createElement("li",null,"/api/info  "),a.a.createElement("li",null,"/api/info/all  "),a.a.createElement("li",null,"/api/sw  "),a.a.createElement("li",null,"/api/login  ")),a.a.createElement("p",null,a.a.createElement("strong",null,"User"),"  "),a.a.createElement("ul",null,a.a.createElement("li",null,"/api/sw/data  "),a.a.createElement("li",null,"/api/info/user  ")),a.a.createElement("p",null,a.a.createElement("strong",null,"Admin"),"  "),a.a.createElement("ul",null,a.a.createElement("li",null,"/api/sw/data  "),a.a.createElement("li",null,"/api/info/user  "),a.a.createElement("li",null,"/api/info/admin  "),a.a.createElement("li",null,"/api/info/fill  ")),a.a.createElement("hr",null),a.a.createElement("h1",null,"Backend"),a.a.createElement("h2",null,"Nedenst\xe5ende er en minimalistisk guide til ops\xe6tning i forbindelse med deployment igennem travis."),a.a.createElement("ol",null,a.a.createElement("li",null,a.a.createElement("p",null,"MySql opret lokal(xxx + xxx_test) og droplet(xxx) database.")),a.a.createElement("li",null,a.a.createElement("p",null,"Netbeans Backend Projekt. (Project Files -> pom.xml). kontroll\xe9r pom.xml linie 18(remote.server). Husk at kontroll\xe9r at der bruges https og ikke http.")),a.a.createElement("li",null,a.a.createElement("p",null,'Netbeans Backend Projekt. (Source packages -> utils -> EMF_Creator.java) s\xf8rg for at connection i EMF_Creator linie 121  "CONNECTION" er unik.')),a.a.createElement("li",null,a.a.createElement("p",null,"Droplet(Terminal/GitBash) ssh ind i dropletten, og brug"),a.a.createElement("ul",null,a.a.createElement("li",null,'sudo nano /opt/tomcat/bin/setenv.sh - opret ny export med v\xe6rdien fra "CONNECTION" i step 3.'))),a.a.createElement("li",null,a.a.createElement("p",null,"Netbeans Backend Projekt (Other sources -> src/main/resources -> default package -> config.properties) kontroll\xe9r database navne i config.properties.")),a.a.createElement("li",null,a.a.createElement("p",null,"Travis.yml fil i rod mappen. \xc6ndre database navn i linie 40(CREATE DATABASE ??) til database navnet valgt i step 1.")),a.a.createElement("li",null,a.a.createElement("p",null,'https://travis-ci.org/ p\xe5 travis s\xe6t REMOTE_USER til "script_user" og REMOTE_PW til script_user\'s password.'),a.a.createElement("p",null,"-- brug f\xf8lgende kommando for at finde password til brugeren hvis n\xf8dvendigt. sudo nano /opt/tomcat/conf/tomcat-users.xml"))),a.a.createElement("p",null,"HUSK AT CLEAN AND BUILD HVIS DER ER ERRORS I IMPORTS."),a.a.createElement("hr",null),a.a.createElement("h1",null,"Frontend"),a.a.createElement("p",null,"This is a client for login in or out of REST endpoint and for fetching data when logged in.",a.a.createElement("br",null),"Set REST endpoint URL in file ",a.a.createElement("strong",null,"src/settings.js"),"  "),a.a.createElement("h2",null,"Deployment instructions"),a.a.createElement("p",null,"First clone project.",a.a.createElement("br",null),"In cloned folder using a terminal enter:  "),a.a.createElement("h4",null,a.a.createElement("code",null,"npm install")),a.a.createElement("p",null,"and"),a.a.createElement("h4",null,a.a.createElement("code",null,"npm install react-router-dom")),a.a.createElement("p",null,"to install prerequisites"),a.a.createElement("p",null,"When all is ready to deploy:"),a.a.createElement("h4",null,a.a.createElement("code",null,"npm run build")),a.a.createElement("h2",null,"Deploy via Surge"),a.a.createElement("p",null,'1 I et f\xe6rdigt react projekt kan man v\xe6lge at deploye via surge. Det foreg\xe5r alt sammen via terminalen(git bash). For at komme i gang, skal man navigere til roden af selve ens projekt. H\xf8jre klik i din projektfolder og find "git bash here". I terminalen skal du skrive "npm run build" (uden citationstegn), hvilket opretter en build mappe, lidt ligesom n\xe5r man i Java f\xe5r en target folder, efter man har builded. '),a.a.createElement("p",null,"2 Hvis man ikke tidligere har benyttet sig af surge, s\xe5 skal man igen i en terminal (git bash) skrive \u201cnpm install -g surge\u201d (uden citationstegn). Det installerer interfacet man skal br /uge for at hoste via surge. Dern\xe6st skal man skrive  "),a.a.createElement("h4",null,a.a.createElement("code",null,"surge --project ./build --domain DITDOM\xc6NENAVN.surge.sh")),a.a.createElement("p",null,"Man skal IKKE skrive hverken .dk eller .com, da .surge.sh er et topdom\xe6ne. DITDOM\xc6NENAVN skal erstattes af hvad du gerne vil have som navn p\xe5 dit projekt. Hvis du ikke har benyttet dig af Surge f\xf8r, vil du blive promptet til at indtaste f\xf8rst en email og dern\xe6st et password. I nogle terminaler er der IKKE noget grafisk der fort\xe6ller dig at det er det du skal. "),a.a.createElement("p",null,"S\xe5dan her ser det f.eks. ud i git bash. "),a.a.createElement("p",null,"Her skal man bare indtaste email f\xf8rst og s\xe5 trykke enter. Dern\xe6st er det tid til et password og s\xe5 enter. Det vil se s\xe5dan her ud hvis det er lykkedes "),a.a.createElement("p",null,"Du kan herefter tilg\xe5 dit react projekt via DITDOM\xc6NENAVN.surge.sh"))};function O(e){var t=e.loggedIn;return console.log("Header"),a.a.createElement("div",null,a.a.createElement("ul",{className:"header"},a.a.createElement("li",null,a.a.createElement(c.b,{exact:!0,activeClassName:"active",to:"/"},"Home")),a.a.createElement("li",null,a.a.createElement(c.b,{activeClassName:"active",to:"/data"},"Data")),a.a.createElement("li",null,a.a.createElement(c.b,{activeClassName:"active",to:"/log"},t?a.a.createElement("div",null,"Logout"):a.a.createElement("div",null,"Login")))))}function N(){return console.log("NoMatch"),a.a.createElement("div",null,"hello NoMatch")}var S=function(){console.log("App");var e=localStorage.getItem("jwtToken"),t=Object(l.useState)(!!e),n=Object(i.a)(t,2),r=n[0],o=n[1];return a.a.createElement("div",null,a.a.createElement(c.a,null,a.a.createElement("div",null,a.a.createElement(O,{loggedIn:r}),a.a.createElement(s.c,null,a.a.createElement(s.a,{exact:!0,path:"/"},a.a.createElement(I,null)),a.a.createElement(s.a,{path:"/data"},a.a.createElement(j,{loggedIn:r})),a.a.createElement(s.a,{path:"/log"},a.a.createElement(E,{facade:p,loggedIn:r,login:function(e,t){console.log("App - login"),p.login(e,t).then((function(e){return o(!0)})),console.log("loggedIn",r)},logout:function(){console.log("App - logout"),p.logout(),o(!1),console.log("loggedIn",r)}})),a.a.createElement(s.a,{component:N})))))};o.a.render(a.a.createElement(S,null),document.getElementById("root"))}},[[23,1,2]]]);
//# sourceMappingURL=main.87c2e87f.chunk.js.map