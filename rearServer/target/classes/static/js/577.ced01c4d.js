"use strict";(self["webpackChunkPandoraNext_TokensTool"]=self["webpackChunkPandoraNext_TokensTool"]||[]).push([[577],{2577:function(e,a,t){t.r(a),t.d(a,{default:function(){return C}});var o=t(6773),s=(t(487),t(3164)),l=(t(6335),t(6252));const n=e=>((0,l.dD)("data-v-596a1437"),e=e(),(0,l.Cn)(),e),r={class:"content_login"},i=n((()=>(0,l._)("meta",{name:"viewport",content:"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"},null,-1))),m={class:"login-container"},d=["src"],c=n((()=>(0,l._)("h4",null,"Pandora-TokensTool",-1))),u={class:"container"},p={style:{display:"flex",transform:"translate(0vw, 2vh)","font-size":"14.6px"}},v={style:{display:"flex",transform:"translate(0vw, 2vh)","margin-top":"5vh","font-size":"14.6px"}},h={style:{display:"flex",transform:"translate(0.5vw, 0vh)","margin-top":"6vh"}},g={style:{display:"block",transform:"translate(0vw, 0vh)","margin-top":"3.5vh"}},f={class:"bottom"},k={style:{"text-align":"center",transform:"translateY(0vh)"}},_={key:0},w=n((()=>(0,l._)("h3",null,[(0,l.Uk)(" 获取token "),(0,l._)("a",{href:"https://chat.OpenAI.com/api/auth/session"},"官网地址 "),(0,l._)("a",{href:"https://ai.fakeopen.com/auth"},"Pandora地址"),(0,l.Uk)(" 欢迎大家来扩展 "),(0,l._)("a",{href:"https://github.com/Yanyutin753/PandoraNext-TokensTool"},"PandoraNext-TokensTool v0.4.8.2 ")],-1))),b=[w],y={key:1},T=n((()=>(0,l._)("h3",null,[(0,l.Uk)(" 获取token "),(0,l._)("a",{href:"https://chat.OpenAI.com/api/auth/session"},"官网地址 "),(0,l._)("a",{href:"https://ai.fakeopen.com/auth"},"Pandora地址"),(0,l._)("br"),(0,l.Uk)(" 欢迎大家来扩展 "),(0,l._)("a",{href:"https://github.com/Yanyutin753/PandoraNext-TokensTool"},"PandoraNext-TokensTool v0.4.8.2 ")],-1))),x=[T];function I(e,a,t,n,w,T){const I=s.gN,P=(0,l.up)("h9"),S=o.XZ;return(0,l.wg)(),(0,l.iD)("div",r,[i,(0,l._)("div",m,[(0,l._)("img",{src:n.image,alt:"Your Image","size:30":""},null,8,d),c]),(0,l._)("div",u,[(0,l._)("div",p,[(0,l.Wm)(I,{modelValue:n.username,"onUpdate:modelValue":a[0]||(a[0]=e=>n.username=e),name:"用户名",label:"用户名",placeholder:"用户名",class:"userName"},null,8,["modelValue"])]),(0,l._)("div",v,[(0,l.Wm)(I,{modelValue:n.password,"onUpdate:modelValue":a[1]||(a[1]=e=>n.password=e),type:"password",name:"密码",label:"密码",placeholder:"密码",class:"userName"},null,8,["modelValue"])]),(0,l._)("div",h,[(0,l.Wm)(S,{class:"remember",modelValue:n.checked,"onUpdate:modelValue":a[2]||(a[2]=e=>n.checked=e),"checked-color":"#0ea27e","icon-size":"13.5px"},{default:(0,l.w5)((()=>[(0,l.Wm)(P,{style:{"font-size":"13.5px",transform:"translateX(7px)"}},{default:(0,l.w5)((()=>[(0,l.Uk)("记住密码")])),_:1})])),_:1},8,["modelValue"])]),(0,l._)("div",g,[(0,l._)("input",{type:"submit",onClick:a[3]||(a[3]=(...e)=>n.submit&&n.submit(...e)),value:"登录",class:"userName"})])]),(0,l._)("div",f,[(0,l._)("div",k,[0==n.page?((0,l.wg)(),(0,l.iD)("div",_,b)):((0,l.wg)(),(0,l.iD)("div",y,x))])])])}var P=t(2262),S=t(2201),U=t(451),N=t(1348),z={setup(){const e=(0,S.tv)(),a=(0,P.iH)(""),t=(0,P.iH)(""),o=(0,P.iH)(""),s=U,n=(0,P.iH)(!0);(0,l.bv)((()=>{const e=localStorage.getItem("savedUsername"),s=localStorage.getItem("savedPassword"),l=localStorage.getItem("savedRemember");"true"===l&&(a.value=e||"",t.value=s||"",o.value=!0),window.innerWidth>767&&(n.value=!1)}));const r=()=>{o.value?(localStorage.setItem("savedUsername",a.value),localStorage.setItem("savedPassword",t.value),localStorage.setItem("savedRemember","true")):(localStorage.removeItem("savedUsername"),localStorage.removeItem("savedPassword"),localStorage.removeItem("savedRemember"));let s={loginUsername:a.value,loginPassword:t.value};fetch("/api/login",{method:"POST",headers:{"Content-Type":"application/json",Authorization:`Bearer ${s}`},body:JSON.stringify(s)}).then((e=>e.json())).then((a=>{if(1===a.code){console.log("登录成功");const t=a.data;localStorage.setItem("jwtToken",t),(0,N.z8)("登录成功！"),setTimeout((()=>{window.innerWidth<=1e3?e.replace("/iphone"):e.replace("/")}),1e3)}else console.error("登录失败"),(0,N.z8)("账号或密码错误！")})).catch((e=>{console.error("登录时出现错误:",e),(0,N.z8)("账号或密码错误！")}))};return{username:a,password:t,image:s,checked:o,submit:r,page:n}}},V=t(3744);const W=(0,V.Z)(z,[["render",I],["__scopeId","data-v-596a1437"]]);var C=W},451:function(e,a,t){e.exports=t.p+"img/chatGpt.93292c55.jpg"}}]);
//# sourceMappingURL=577.ced01c4d.js.map