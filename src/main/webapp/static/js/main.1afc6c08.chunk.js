(this.webpackJsonpreact_security=this.webpackJsonpreact_security||[]).push([[0],{20:function(e,t,a){e.exports=a(34)},25:function(e,t,a){},26:function(e,t,a){},27:function(e,t,a){},34:function(e,t,a){"use strict";a.r(t);var n=a(0),c=a.n(n),r=a(17),l=a.n(r),o=(a(25),a(26),a(27),a(11)),i=a(7),s="http://localhost:8080/testexamexercise1",u=a(9),d=a(5);var m=function(e){return Object(u.a)(e),console.log("FillDB"),c.a.createElement("div",null,c.a.createElement("h2",null,"FillDB"))};var E=function(e){return Object(u.a)(e),console.log("EmptyDB"),c.a.createElement("div",null,c.a.createElement("h2",null,"EmptyDB"))};var v=function(e){return Object(u.a)(e),console.log("Person"),c.a.createElement("div",null,c.a.createElement(i.a,null,c.a.createElement("div",{className:"newsContainer"},c.a.createElement("div",{className:"newsContent"},c.a.createElement(d.c,null,c.a.createElement(d.a,{exact:!0,path:"/fill"},c.a.createElement(m,null)),c.a.createElement(d.a,{exact:!0,path:"/empty"},c.a.createElement(E,null)))))))};function f(){return console.log("MenuBar"),c.a.createElement("div",null,c.a.createElement("ul",{className:"header"},c.a.createElement("li",null,c.a.createElement(i.b,{exact:!0,activeClassName:"active",to:"/"},"Home")),c.a.createElement("li",null,c.a.createElement(i.b,{exact:!0,activeClassName:"active",to:"/fill"},"Fill DB")),c.a.createElement("li",null,c.a.createElement(i.b,{exact:!0,activeClassName:"active",to:"/empty"},"Empty DB"))))}var p=function(e){var t=e.apiFacade;console.log("App");var a=localStorage.getItem("chosenTeam"),r=localStorage.getItem("chosenTeamCrestUrl"),l=Object(n.useState)([]),s=Object(o.a)(l,2),u=(s[0],s[1],Object(n.useState)(a||"")),d=Object(o.a)(u,2),m=(d[0],d[1],Object(n.useState)(r||"")),E=Object(o.a)(m,2),p=(E[0],E[1],Object(n.useState)("")),h=Object(o.a)(p,2);return h[0],h[1],Object(n.useEffect)((function(){console.log("useEffect"),console.log("App useEffect standings");console.log("App useEffect - urlStandings","http://localhost:8080/testexamexercise1/api/fb/standings")}),[],t),c.a.createElement("div",null,c.a.createElement(i.a,null,c.a.createElement("div",null,c.a.createElement("div",{className:"container"},c.a.createElement("button",{type:"button",name:"daynight",id:"daynight"},c.a.createElement("i",{className:"fa fa-sun-o","aria-hidden":"true",id:"sun"}),c.a.createElement("i",{className:"fa fa-moon-o","aria-hidden":"true",id:"moon"})),c.a.createElement("div",{id:"top-content"})),c.a.createElement("div",{className:"container"},c.a.createElement("div",{id:"header"},c.a.createElement("div",{id:"logo"}),c.a.createElement("div",{id:"banner"}))),c.a.createElement("div",{className:"container"},c.a.createElement("div",{id:"menubar"},c.a.createElement(f,null))),c.a.createElement("div",{className:"container"},c.a.createElement("div",{id:"newsticker"})),c.a.createElement("div",{className:"container"},c.a.createElement("div",{id:"cont-1"},c.a.createElement("div",{id:"team"}),c.a.createElement("div",{id:"userinfo"})),c.a.createElement("div",{id:"cont-2"},c.a.createElement("div",{id:"part-1"},c.a.createElement("div",{id:"nextmatch"},"/>"),c.a.createElement("div",{id:"buy"})),c.a.createElement("div",{id:"news"},c.a.createElement(v,null))),c.a.createElement("div",{id:"cont-3"},c.a.createElement("div",{id:"leaderboard"}))))))},h=a(6),g=a.n(h);function b(e,t){var a={method:e,headers:{"Content-type":"application/json"}};return t&&(a.body=JSON.stringify(t)),a}function y(e){return e.ok?e.json():Promise.reject({status:e.status,fullError:e.json()})}var x={getData:function(e){return console.log("apiFacade - getData"),Promise.resolve(fetch(e)).then(y).then((function(e){return console.log("apifacade - getData data",e),e})).catch(console.log.bind(console))},getDataAsync:function(e){var t;return g.a.async((function(a){for(;;)switch(a.prev=a.next){case 0:return console.log("apiFacade - getDataAsync"),a.next=3,g.a.awrap(fetch(e).then(y).then((function(e){return console.log("apiFacade - getDataAsync - data",e),e})).catch(console.log.bind(console)));case 3:return t=a.sent,console.log("apiFacade - getDataAsync - data",t),a.abrupt("return",t);case 6:case"end":return a.stop()}}))},addEditPerson:function(e){var t,a,n,c;return g.a.async((function(r){for(;;)switch(r.prev=r.next){case 0:if(void 0!==e.id){r.next=8;break}return t=b("POST",e),r.next=4,g.a.awrap(fetch(s,t).then(y));case 4:return a=r.sent,r.abrupt("return",a);case 8:return n=b("PUT",e),r.next=11,g.a.awrap(fetch(s+"/"+e.id,n).then(y));case 11:return c=r.sent,r.abrupt("return",c);case 13:case"end":return r.stop()}}))},deletePerson:function(e){var t,a;return g.a.async((function(n){for(;;)switch(n.prev=n.next){case 0:return t=b("DELETE"),n.next=3,g.a.awrap(fetch(s+"/"+e,t).then(y));case 3:return a=n.sent,n.abrupt("return",a);case 5:case"end":return n.stop()}}))}};l.a.render(c.a.createElement(p,{apiFacade:x}),document.getElementById("root"))}},[[20,1,2]]]);
//# sourceMappingURL=main.1afc6c08.chunk.js.map