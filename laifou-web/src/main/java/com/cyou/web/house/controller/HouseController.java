/*
 * 文 件 名:  HousePriceController.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.web.house.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cyou.laifou.house.model.Condition;
import com.cyou.laifou.house.model.House;
import com.cyou.laifou.houseprice.service.HousePriceService;
import com.cyou.lucene.search.model.SearchIndexCondition;
import com.cyou.lucene.search.service.SearchService;
import com.cyou.web.common.Pagination;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/house")
public class HouseController
{
    @Autowired
    private HousePriceService housePriceService;
    
    @Autowired
    private SearchService searchService;
    
    @RequestMapping(value="/price",method=RequestMethod.GET)
    public ModelAndView getHouseInfosByCityId(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/house/houselist");
        int totalCount = housePriceService.count(new Condition());
        Pagination page = new Pagination(1, totalCount, 10);
        mv.addObject("page", page);
        mv.addObject("condition", new Condition());
        mv.addObject("currentPage", 1);
        List<House> houses = housePriceService.queryHouses(new Condition());
        mv.addObject("houses", houses);
        return mv;
    }
    
    @RequestMapping(value="/price/condition",method=RequestMethod.POST)
    public ModelAndView getHouseInfosByCondition(HttpServletRequest request,@ModelAttribute("condition") Condition condition){
//        Condition condition = new Condition()
//        @RequestParam("currentPage") int currentPage,@RequestParam("cityName") String cityName
        if (condition.getHouseId()>0)
        {
            List<Long> ids = condition.getIds();
            if (ids!=null)
            {
                ids.add(condition.getHouseId());
                condition.setIds(ids);
            }else {
                ids=new ArrayList<Long>();
                ids.add(condition.getHouseId());
                condition.setIds(ids);
            }
        }
        ModelAndView mv = new ModelAndView("/house/houselist");
        int totalCount = housePriceService.count(condition);
        Pagination page = new Pagination(condition.getCurrentPage(), totalCount,10);
        mv.addObject("page", page);
        mv.addObject("condition", condition);
        mv.addObject("currentPage", condition.getCurrentPage());
        List<House> houses = housePriceService.queryHouses(condition);
        mv.addObject("houses", houses);
        return mv;
    }
    
    @RequestMapping("/search")
    public ModelAndView searchByIndex(HttpServletRequest request,@ModelAttribute("indexCondition ") SearchIndexCondition indexCondition){
        ModelAndView mv = new ModelAndView("/house/indexSearchlist");
        try
        {
            List<House> houses = new ArrayList<House>();
            List<Document> documents = searchService.getIdsByCondition(indexCondition);
            for (Document document : documents)
            {
                House house = new House();
                house.setId(Long.parseLong(document.get("id")));
                house.setAddress(document.get("address"));
                house.setCityName(document.get("cityname"));
                house.setHouseName(document.get("housename"));
                house.setPrice(Integer.parseInt(document.get("price")));
                house.setKfsName(document.get("kfs"));
                house.setCreateTime(new Date(Long.parseLong(document.get("createtime"))));
                houses.add(house);
                
            }
            mv.addObject("houses", houses);
            
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (ParseException e)
        {
            System.out.println(e);
        }
        mv.addObject("housename", indexCondition.getHousename());
        mv.addObject("minPrice", indexCondition.getMinPrice());
        mv.addObject("maxPrice", indexCondition.getMaxPrice());
        return mv;
    }
    
    
    @RequestMapping(value="/searchHousename",method=RequestMethod.POST)
    public ModelMap searchHousename(HttpServletRequest request,@RequestParam("housename") String housename,ModelMap model){
        
        Set<String> housnameList=new HashSet<String>();
        try
        {
            
            SearchIndexCondition indexCondition = new SearchIndexCondition();
            indexCondition.setHousename(housename);
            List<Document> documents = searchService.getIdsByCondition(indexCondition);
            for (Document document : documents)
            {
                housnameList.add(document.get("housename"));
            }
            model.addAttribute("housenames", housnameList);
            model.addAttribute("success", 0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            model.addAttribute("success", 1);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            model.addAttribute("success", 1);
        }
        return model;
    }
    
    @RequestMapping("/statis")
    public ModelAndView statistics(HttpServletRequest request,@RequestParam("housename") String housename){
        ModelAndView mv = new ModelAndView("/house/statistics");
        TimeSeries timeSeries = new TimeSeries(housename+"价格趋势表", Day.class);
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        try
        {
            List<Document> documents = searchService.getIdsByHouseName(housename);
            for (Document document : documents)
            {
                Date createTime = new Date(Long.parseLong(document.get("createtime")));
                Calendar cal = Calendar.getInstance();  
                cal.setTime(createTime);
                Day day = new Day(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
                timeSeries.add(day, Integer.parseInt(document.get("price")));
            }
            lineDataset.addSeries(timeSeries);
            JFreeChart chart = ChartFactory.createTimeSeriesChart("访问量统计时间线", "时间", "价格", lineDataset, true, true, true);
            
            XYPlot plot = (XYPlot) chart.getPlot();
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)plot.getRenderer();
            //设置网格背景颜色
            plot.setBackgroundPaint(Color.white);
            //设置网格竖线颜色
            plot.setDomainGridlinePaint(Color.pink);
            //设置网格横线颜色
            plot.setRangeGridlinePaint(Color.pink);
            //设置曲线图与xy轴的距离
            plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));
            //设置曲线是否显示数据点
            xylineandshaperenderer.setBaseShapesVisible(true);
            //设置曲线显示各数据点的值
            XYItemRenderer xyitem = plot.getRenderer();   
            xyitem.setBaseItemLabelsVisible(true);   
            xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
            xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
            xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 14));
            plot.setRenderer(xyitem);
            
            DateAxis xAxis = (DateAxis) plot.getDomainAxis();// X横轴刻度设计
            xAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, 1,new SimpleDateFormat("yyyy-MM-dd")));
            
            NumberAxis valueAxis =  (NumberAxis)plot.getRangeAxis();
            valueAxis.setAutoTickUnitSelection(false);
            NumberTickUnit nt= new NumberTickUnit(10d);
            valueAxis.setTickUnit(nt);
            //设置子标题
            TextTitle subtitle = new TextTitle(housename, new Font("黑体", Font.BOLD, 12));
            chart.addSubtitle(subtitle);
            
            //设置主标题
            chart.setTitle(new TextTitle(housename+"价格趋势统计", new Font("隶书", Font.ITALIC, 15)));
            chart.setAntiAlias(true);
            String filename = ServletUtilities.saveChartAsPNG(chart, 1000, 300, null, request.getSession());
            String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
            mv.addObject("filename", filename);
            mv.addObject("graphURL", graphURL);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
       
        return mv;
    }
    
    
    
    
    @RequestMapping("/deleteLuceneIndex/{id}")
    public ModelMap deleteIndex(HttpServletRequest request,@PathVariable("id") Long id,ModelMap model){
        searchService.deleteIndex(id);
        model.addAttribute("success", 0);
        return model;
    }
    
    @RequestMapping("/addIndex/{id}")
    public ModelMap addIndex(HttpServletRequest request,@PathVariable("id") Long id,ModelMap model){
        searchService.addIndex(id);
        model.addAttribute("success", 0);
        return model;
    }
    
    
    
    
    @RequestMapping("/indexAllRebuild")
    public ModelMap rebuildAllIndex(HttpServletRequest request,ModelMap model){
        searchService.initAllIndex();
        model.addAttribute("success", 0);
        return model;
    }
    
    @RequestMapping("/mergeNewindex")
    public ModelMap mergeNewindex(HttpServletRequest request,ModelMap model){
        searchService.initNewIndex();
        model.addAttribute("success", 0);
        return model;
    }
    
    
    /**
     * form表单提交 Date类型数据绑定
     * <功能详细描述>
     * @param binder
     * @see [类、类#方法、类#成员]
     */
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
}
