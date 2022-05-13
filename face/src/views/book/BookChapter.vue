<template>
  <div class="chapter">
    <main>
      <h2 style="text-align: center; font-size: 2em">{{ chapter.name }}</h2>
      <h3 style="text-align: right; font-size: 0.8em">{{ chapter.time }}</h3>
      <article>
        <p v-for="item in chapter.content" :key="item.id">{{ item }}</p>
      </article>
    </main>
    <aside>
      <a-divider
        ><h2 style="font-size: 1.5em">{{ chapter.bookTitle }}</h2></a-divider
      >
      <a-image
        :width="200"
        :height="200"
        :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
        fallback="data:image/png;base64,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"
      />
      <a-divider><h2>目录</h2></a-divider>
      <a-collapse v-model:activeKey="activeKey" :bordered="false">
        <a-collapse-panel
          :header="item.name"
          v-for="item in chapter.catalog"
          :key="item.id"
        >
          <a-button type="ghost" v-for="it in item.chapters" :key="it.id">
            {{ it.name }}
          </a-button>
        </a-collapse-panel>
      </a-collapse>
    </aside>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";

const chapter = ref({
  id: "101",
  bookTitle: "火之异能者",
  name: "序章",
  time: "2022/05/13 12:20",
  catalog: [
    {
      id: "101",
      name: "第一卷 狂僵异变",
      chapters: [
        {
          id: "101",
          name: "第一章 开幕",
        },
        {
          id: "102",
          name: "第二章 异变",
        },
        {
          id: "103",
          name: "第三章 拯救",
        },
        {
          id: "104",
          name: "第四章 打扫",
        },
        {
          id: "105",
          name: "第五章 据点",
        },
        {
          id: "106",
          name: "第六章 入侵",
        },
        {
          id: "107",
          name: "第七章 抛弃",
        },
      ],
    },
    {
      id: "102",
      name: "第二卷 生存方舟",
      chapters: [],
    },
    {
      id: "103",
      name: "第三卷 死亡世界",
      chapters: [],
    },
  ],
  content: [
    `“迪克！”`,
    `我醒来的时候，发现有人摇着我的肩膀。不，应该说我是被摇醒的也不奇怪吧？`,
    `摇醒我的是一个有着猫耳脸颊上有两条红色印记的兽人。`,
    `她的外表相当于人类的十四五岁左右，她总是很喜欢蹭我的下巴，一开始我有些抵抗，不过后来也就渐渐地不再抵抗了，因为她地头发蹭过来相当地舒服。`,
    `“嗯？莉莉丝？怎么了？”`,
    `我轻轻地抚摸了她的额头，她露出了开心的表情。`,
    `“嘿嘿嘿……迪克，早饭，迪克，早饭……”`,
    `她的尾巴不停地摇摆着，就像是向喜欢的主人不停地表述喜悦一样的幼犬一样。`,
    `天空还是昏黑色，想起这两周所经历的事情，心中总觉得有些许开朗。`,
    `原本只是灰黑色的世界，在这两周的时间里变得有些许色彩了，也是多亏了这些家伙的帮忙吧。`,
    `“迪克，快来吃吧，今天有清汤哦！”`,
    `在卧室门外向我招手的，是穿着围裙的犬耳亚人温蒂。`,
    `与蓝色短发的莉莉丝不同，她留着到胸口长度的金色头发，平时都是散落下来，不过现在是两边扎起来的，应该是为了不沾到多余的灰尘吗？`,
    `不过那副模样，很像一个贤淑的妻子呢——除了她看起来是个十二三岁的模样以外。`,
    `我推开莉莉丝后就下了床，忽视背后生气拿打枕头出气的莉莉丝，就穿上了我的夹克，向洗脸间走去。`,
    `这个房间虽然陪着我的时间只有短短的两周，然而这两周的时间却像两个月那样的漫长与快乐，除了一些事情之外。`,
    `虽然也不是说不回来了，不过接下来的旅途应该需要不少时间吧？`,
    `虽然昨天趁着最后的一天在城里陪着莉莉丝和温蒂逛了个遍，但毕竟临走的时候，总是还是有些许留念的，也许就再也回不……不行不行，不能想这么可怕的事情。`,
    `我张大了嘴巴，让清水在我的口腔里滚动了几番之后才把它们和刷牙的泡沫吐了出来。`,
    `右手随意一番，把封印在体内的魔法未经魔法阵构建就一瞬间激发了出来——【水球术】。`,
    `五六个水球掉进了脸盆里，我这才抓起挂起来的毛巾浸在水中，洗了两次毛巾之后，拧干七成的水分擦拭自己的脸。`,
    `冰凉的刺骨感瞬间钻进我的脸庞。`,
    `啊……这也太冷了吧？现在是春季没错吧？总给人一种冬季的感觉，难道是因为温度还没有回过来吗？`,
    `我艰难地上下擦拭着脸，在十次左右后才重新扔回了脸盆，洗了三次之后重新放回原地。`,
    `走出洗脸间后我看见莉莉丝从我的房间里走了出来，只穿着上衣。`,
    `“迪克！帮我洗脸！”`,
    `她像个小孩子一样冲了过来抱住我的腰，撒着娇。`,
    `虽然她一直都不怎么喜欢洗脸，不过这两周下来似乎也养成习惯了，只不过总是还是要我帮忙洗，虽然我觉得她应该已经清楚了方法才对……`,
    `我把水球扔进了脸盆，然后把毛巾浸湿后在她的脸庞上轻轻地擦拭着。`,
    `“唔……好冷~~”`,
    `她的话语最后拖了很长，看来这冰冷的感觉就连她也受不了。我慢慢地在她的脸庞、脖子和耳边都擦了一遍。`,
    `她全程都闭着眼睛，大概是因为毛巾的温度被脸庞温热了，所以她也不再抱怨冷了，而是微微皱着眉头，表现出一副舒服的表情。`,
    `再经历了三重这样的往复之后，我们终于从洗脸间出来了，走到了起居室。`,
    `坐在座位上的是微微闭着眼睛显得昏昏欲睡的温蒂，在她的面前，也就是餐桌上摆着三人份的煎蛋、面包、清汤、牛奶，还有一罐果酱。`,
    `也许是因为早起准备导致的睡眠不足吧？就这样让她休息一会……`,
    `“温蒂！早饭！”`,
    `莉莉丝精神的话语让温蒂惊醒了，她擦了擦嘴角的口水，看到我们站在面前。`,
    `“啊，迪克，温蒂……欸……对、对不起，我居然睡着了”`,
    `“睡眠不足？“`,
    `莉莉丝歪着脑袋看着温蒂。`,
    `而我摇了摇头。`,
    `“没事，应该是为了早起才睡眠不足的吧？待会路上我架马车，你就好好补一下觉吧，现在就一起吃早饭吧”`,
    `我和莉莉丝坐在了温蒂的对面，不知为何温蒂狠狠地瞪了一眼莉莉丝，然后把脸颊鼓了起来。`,
    `莉莉丝摇着尾巴和离开地面的脚，开心地咬着面包。`,
    `而我则是把果酱的盖子打开，盛了满满的一勺涂在温热的面包的表层上。`,
    `因为是各种水果的果肉混合在一起的，所以有各种酸甜的味道，不知道其他的城市里有没有这样的果酱。`,
    `煎蛋也涂上果酱……果然美味！`,

    `我们花了十分钟左右就解决了早饭，之后就走到房子的后院，做起了准备。`,
    `莉莉丝与我一同将行李从房间里搬到马车里，莉莉丝则是在马车边上和抓着蝴蝶在玩耍。`,
    `原本以为没有多少的行李，但是到了搬运的时候却发觉出奇地多，于是我向旁边地温蒂询问。`,
    `“啊，您是说行李比想象地多？是吗？“`,
    `“是啊，我刚来这个城市的时候，可是只身一人啊，根本也就没有什么物品，可是你看至少已经搬了十个箱子了，有那么多吗？“`,
    `我指着马车上的木箱，那里确实摆着整齐地三叠，一共有十个箱子。`,
    `“啊……那个啊……其实大部分都是……”`,
    `“大部分都是你们的行李吗？”`,
    `她的肩膀抖动了一下，好像被猜中了有些尴尬地把视线别开。`,
    `“嗯……如果是这样的话，其实也无所谓了，只要不是一些没有用的东西就好了……”`,
    `她的肩膀再次抖动了一下，脸上维持笑容都有些费力。`,
    `我摇了摇脑袋，真是的，女孩子的行李可真是多啊——我只能这样感叹，然后祈祷着她们的行李不会占满马车的车棚全部的空间。`,
    `不过实际上在那之后也就只有五个箱子，也就只占了三分之一的空间，话说如此，但是就连窗户都挡住了。`,
    `虽然箱子数量多，不过重量倒是不太重，难道是衣服之类的吗？果然女孩子就是在这些上面很在意的吧，那我也没有什么好说的。顺便一提，我的衣服在那些箱子里也就只有四件而已。`,
    `原本温蒂也想继续担驾驶员，可是我坚持让她在车棚里休息，经过了大约十次的纠缠之后，她才答应了下来，和莉莉丝一起钻进了车棚里。`,
    `“温蒂！快来快来！你看那边！”`,
    `我在车棚的后面，跟着莉莉丝手指的方向，望向了东边的天空。`,
    `不知不觉之间，天空已经泛白了，东边天空的云朵被钻出半个脑袋的太阳烧地通红。客观来看实际上根本分不清是朝霞还是晚霞，但是实际上面对这样的美景，根本没有必要在意这些细节。`,
    `“好美啊！”`,
    `温蒂的眼睛里闪烁了一下，莉莉丝也露出了高兴的笑容。`,
    `我刻意地咳嗽了一声，打破了现在浪漫的氛围。`,
    `“好了好了，接下来要上路了，你们准备好了没有啊？”`,
    `我再刻意地提了嗓子。`,
    `没错，我们接下来要离开这个城镇，踏上属于我们自己的旅途。`,
    `“好了好了！”`,
    `“嗯”`,
    `莉莉丝高举着右手，而温蒂则双手握拳，两个人的情绪十分高涨，对于一个要踏上旅途的人来说，这是很重要的。`,
    `我点了点头，坐在了前方的驾驶台上，挥起了驾驶绳，马向前缓缓移动，我们走上了前往城门的大道。`,
    `这是一个新的旅途，对于我们新生的薪火佣兵团来说。`,
    `眼前的世界已经与两周前那灰暗、冰冷、模糊的世界不一样了，变得明亮、温热、清晰起来了。`,
  ],
});
</script>

<style scoped>
p {
  text-indent: 2em;
}

.chapter {
  display: flex;
  width: 80%;
  margin: auto;
}

.chapter main {
  width: 60%;
  margin-right: 10%;
}

.chapter aside {
  width: 20%;
  margin-left: 10%;
}
</style>