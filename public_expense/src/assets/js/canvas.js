/*
 * @Author: 李无敌
 * @Date: 2021-04-07 14:40:31
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-04-07 16:07:45
 * @FilePath: \myworkpace\iview-app\src\common\js\canvas.js
 */
import $ from 'jquery';
let i;
export  function canvas() {
  try {
    $(function () {
      function colorValue(min) {
        return Math.floor(Math.random() * 255 + min);
      }
      function createColorStyle(r, g, b) {
        return 'rgba(' + r + ',' + g + ',' + b + ', 0.8)';
      }
      function Color(min) {
        min = min || 0;
        this.r = colorValue(min);
        this.g = colorValue(min);
        this.b = colorValue(min);
        this.style = createColorStyle(this.r, this.g, this.b);
      }
      const canvas = document.querySelector('canvas');
      const ctx = canvas.getContext('2d')
      canvas.width = $(window).width();
      canvas.height = $(window).height();
      ctx.lineWidth = .3;
      ctx.strokeStyle = (new Color(150)).style;

      const mousePosition = {
        x: 30 * canvas.width / 100,
        y: 30 * canvas.height / 100
      };

      const dots = {
        nb: 250,
        distance: 100,
        dRadius: 150,
        array: []
      };
  

  

  
      function mixComponents(comp1, weight1, comp2, weight2) {
        return (comp1 * weight1 + comp2 * weight2) / (weight1 + weight2);
      }
  
      function averageColorStyles(dot1, dot2) {
        const color1 = dot1.color,
          color2 = dot2.color;

        const r = mixComponents(color1.r, dot1.radius, color2.r, dot2.radius),
          g = mixComponents(color1.g, dot1.radius, color2.g, dot2.radius),
          b = mixComponents(color1.b, dot1.radius, color2.b, dot2.radius);
        return createColorStyle(Math.floor(r), Math.floor(g), Math.floor(b));
      }
  

  
      function Dot() {
        this.x = Math.random() * canvas.width;
        this.y = Math.random() * canvas.height;
  
        this.vx = -.5 + Math.random();
        this.vy = -.5 + Math.random();
  
        this.radius = Math.random() * 2;
  
        this.color = new Color();
      }
  
      Dot.prototype = {
        draw: function () {
          ctx.beginPath();
          ctx.fillStyle = this.color.style;
          ctx.arc(this.x, this.y, this.radius, 0, Math.PI, false);
          ctx.fill();
        }
      };
  
      function createDots() {
        for (i = 0; i < dots.nb; i++) {
          dots.array.push(new Dot());
        }
      }
  
      function moveDots() {
        for (i = 0; i < dots.nb; i++) {

          const dot = dots.array[i];
  
          if (dot.y < 0 || dot.y > canvas.height) {
            dot.vy = -dot.vy;
          } else if (dot.x < 0 || dot.x > canvas.width) {
            dot.vx = -dot.vx;
          }
          dot.x += dot.vx;
          dot.y += dot.vy;
        }
      }
  
      function connectDots() {
        for (i = 0; i < dots.nb; i++) {
          for (let j = 0; j < dots.nb; j++) {
            const iDot = dots.array[i];
            const jDot = dots.array[j];
  
            if ((iDot.x - jDot.x) < dots.distance && (iDot.y - jDot.y) < dots.distance && (iDot.x - jDot.x) > -dots.distance && (iDot.y - jDot.y) > -dots.distance) {
              if ((iDot.x - mousePosition.x) < dots.dRadius && (iDot.y - mousePosition.y) < dots.dRadius && (iDot.x - mousePosition.x) > -dots.dRadius && (iDot.y - mousePosition.y) > -dots.dRadius) {
                ctx.beginPath();
                ctx.strokeStyle = averageColorStyles(iDot, jDot);
                ctx.moveTo(iDot.x, iDot.y);
                ctx.lineTo(jDot.x, jDot.y);
                ctx.stroke();
                ctx.closePath();
              }
            }
          }
        }
      }
  
      function drawDots() {
        for (i = 0; i < dots.nb; i++) {
          const dot = dots.array[i];
          dot.draw();
        }
      }
  
      function animateDots() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        moveDots();
        connectDots();
        drawDots();
  
        requestAnimationFrame(animateDots);
      }
  
      $('canvas').on('mousemove', function (e) {
        mousePosition.x = e.pageX;
        mousePosition.y = e.pageY;
      });
  
      $('canvas').on('mouseleave', function () {
        mousePosition.x = canvas.width / 2;
        mousePosition.y = canvas.height / 2;
      });

      createDots();
      requestAnimationFrame(animateDots);
    });
  } catch (e) {
    console.log(e);
  }
}