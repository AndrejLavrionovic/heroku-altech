/**
 * 
 */

$(document).ready(function(){
	
	var cond = [
	    {
	    	'field' : 'Firstname',
	    	'condition' : [
	    	    'Firstname starts with capital letter',
	    	    'Firstname contains letters and could contain apostrophe (\')',
	    	    'Firstname could contain two parts separated by hyphen (-)',
	    	    'Firstname could contain two parts separated by white spase',
	    	    'Firstname must be at most 25 letters long',
	    	    'If firstname contains two parts separated by hyphen or white spase, the second part must be at most 20 letters long'
	    	]
	    },
	    {
	    	'field' : 'Lastname',
	    	'condition' : [
                'Lastname starts with uppercase letter',
                'Lastname contains letters and could contain apostrophe (\')',
                'Lastname could contain two parts separated by hyphen (-)',
	    	    'Lastname could contain two parts separated by white spase',
	    	    'Lastname must be at most 25 letters long',
	    	    'If Lastname contains two parts separated by hyphen or white spase, the second part must be at most 20 letters long'
	        ]
	    },
	    {
	    	'field' : 'Telephone',
	    	'condition' : [
	    	    'Telephone must contain only numbers (0-9)',
	    	    'Second part of telephone number must be 7 digits long'
	    	]
	    },
	    {
	    	'field' : 'Email',
	    	'condition' : [
	    	    'Email contains @altech.ie sufix',
	    	    'Email address could contain two parts separated by dot (.)',
	    	    'Each part of email must be at most 25 charachters',
	    	    'Email could consist of letters and numbers'
	    	]
	    },
        {
            'field' : 'Username',
            'condition' : [
                'Username must be unique',
                'Username must be 3-32 characters long',
                'Username could contain uppercase, lowercase letters, numbers or underscore (_)'
            ]
        },
        {
            'field' : 'Password',
            'condition' : [
                'Password must be unique',
                'Password must be at least 8 characters long',
                'Password must be at most 32 characters long',
                'Password must contain at least one uppercase letter (e.g. A, B, C)',
                'Password must contain at least one lowercase letter (e.g. a, b, c)',
                'Password must contain at least one number (e.g. 1, 2, 3)',
                'Password must contain at least one symbol (e.g. #, !, ?, $, & etc.)'
            ]
        }
    ];
	
	var modalContent=function(index){
		$('#conditions').empty();
        $('#fieldName').html(cond[index].field);
        for(var i=0; i<cond[index].condition.length; i++){
            $('#conditions').append('<li>' + cond[index].condition[i] + '</li>');
        }
	}
	
	$('.spanModal').click(function(event){
		if($(event.target).is('#modalFirstname')){
			modalContent($('.spanModal').index($(event.target)));
		}
		else if($(event.target).is('#modalLastname')){
			modalContent($('.spanModal').index($(event.target)));
		}
		else if($(event.target).is('#modalTel')){
			modalContent($('.spanModal').index($(event.target)));
		}
		else if($(event.target).is('#modalEmail')){
			modalContent($('.spanModal').index($(event.target)));
		}
		else if($(event.target).is('#modalUsername')){
			modalContent($('.spanModal').index($(event.target)));
		}
		else if($(event.target).is('#modalPassword')){
			modalContent($('.spanModal').index($(event.target)));
		}
        $('.modal').css({
            'display': 'block'
        });
	});
	$('#imgClose').click(function(){
        $('.modal').css({'display': 'none'});
    })
});