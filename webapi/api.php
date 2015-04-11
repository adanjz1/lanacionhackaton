<?php
include 'rssparser.php';




                $random = rand(0,100);
                if($random > $_GET['porcentaje']){
                    $r = getNewsBanner();    

                }else{
                    $cats = array();
                    if(!empty($_GET['gastronomia'])){
                        $cats[] = utf8_decode('Gastronomía');
                    }
                    if(!empty($_GET['turismo'])){
                        $cats[] = utf8_decode('Turismo');
                    }
                    if(!empty($_GET['entretenimiento'])){
                        $cats[] = utf8_decode('Entretenimiento');
                    }
                    if(!empty($_GET['cuidadoPersonal'])){
                        $cats[] = utf8_decode('Cuidado Personal');
                    }
                    if(!empty($_GET['moda'])){
                        $cats[] = utf8_decode('Moda');
                    }
                    if(!empty($_GET['masCategorias'])){
                        $cats[] = utf8_decode('Más Categorías');
                    }
                    $r = getBanner($cats,($_GET['tarjeta'])?'Premium':'Classic');
                }

                echo json_encode($r);
                    
                    
           
         function getBanner($categorias,$tarjeta){
           
                        
                $json = file_get_contents("http://23.23.128.233:8080/api/geo/-34.5332422/-58.4672752");
                
                $array = (array)json_decode($json);
                $auxCounter = 0;
                while(empty($ret)){
                    $auxCounter++;
                    if($auxCounter == 50){
                        return getNewsBanner();
                    }
                    $i = rand(0,(count($array)-1));
                    if((in_array(utf8_decode($array[$i]->beneficio->categoria),$categorias) || empty($categorias)) && in_array($tarjeta,explode('-',$array[$i]->beneficio->tarjeta))){
                        $arrayImagen = explode(':',$array[$i]->imagen);
                        $nombreImagen = str_replace('nombre=', '', $arrayImagen[0]);
                        $ret['html'] = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                <html xmlns="http://www.w3.org/1999/xhtml"><head>
                    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                </head>
                    <body>
                    <div>'
                    . '<div>'
                        . '<div>
                        <img width="200px" height="200px" src="http://club.lanacion.com.ar/imagenes/'.$nombreImagen.'">'
                            
                        . '<div><big><big>'
                            .$array[$i]->beneficio->tipo.' en '.$array[$i]->establecimiento->nombre
                        . '</big></big></div>'
                        . '<div><big>'
                            .$array[$i]->beneficio->descripcion
                        . '</big></div>'
                        
                    . '</div>'
                . '</div></body></html>';
                    $ret['link'] = 'https://www.google.com/maps/place/'.$array[$i]->point[0].','.$array[$i]->point[1];
                    }
                }
                $ret['bannerId'] = 9998;
                $ret['banner']  = '';
                $ret['type']  = 'html-banner';
                $ret['success'] = true;
                return $ret;
        }
     function getNewsBanner(){
            
            try{
                
                $rs = new RSSParser(array('url' => 'http://contenidos.lanacion.com.ar/herramientas/rss/origen=2','life'=>0));
                @$data = $rs->getFeed(100);
            }catch(Exception $e){
                
                return getNewsBanner();
            }

            $i = rand(0,(count($data)-1));
                  if(empty($data[$i]['link'])){
                        return getNewsBanner();
                    }
                    $link = (utf8_decode($data[$i]['link']));
                    $extraMargin=0;
                    $title = $data[$i]['title'];
                    
              
        
            $data['title'] = strip_tags(utf8_decode($title));
            $html = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                <html xmlns="http://www.w3.org/1999/xhtml"><head>
                    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                </head>
                    <body>
                    <div>'
                    . '<div>'
                        . '<div><big><big>'
                            .  utf8_encode($data['title'])
                        . '</big></big></div>'
                    . '</div>'
                . '</div></body></html>';
            
            $ret['bannerId'] = 9999;
            
            $ret['banner']  = '';
            $ret['link']  = $link;
            $ret['type']  = 'html-banner';
            $ret['html']  = $html;
            $ret['success'] = true;
            return $ret;
        }

