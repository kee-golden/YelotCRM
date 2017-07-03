/**
 * Created by gc on 2017/4/17 0017.
 */

require(['jquery', 'yaya','dateTimePicker'], function ($, yaya) {

  $(' .cel_tab tbody tr').click(function () {
      $(this).addClass('red');
      $(this).siblings().removeClass('red');
  })

})
