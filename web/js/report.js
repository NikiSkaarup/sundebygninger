/**
 * Created by Niki on 2016-10-28.
 */

var counter = 1;

$("#add_room").on('click', function () {
    console.log("Creating room " + counter);
    $('#room-' + counter).after(createRoom());
    console.log("Created room " + counter);
});

var formGroup = $(document.createElement('div')).addClass('form-group');
var input = $(document.createElement('input')).addClass('form-control');
var label = $(document.createElement('label')).addClass('control-label');

function createRoom() {
    console.log("Incrementing counter: " + counter);
    counter++;
    console.log("counter: " + counter);

    var room = formGroup.clone().attr({id: 'room-' + counter});
    room.attr({'data-commentCount': 1});//$('#room-'+counter).attr({'data-commentCount':1});

    room.append($(document.createElement('hr')));
    room.append(label.clone()
            .addClass('col-md-3')
            .text('Lokale ' + counter));

    var div1 = $(document.createElement('div')).addClass('col-md-9');
    room.append(div1);

    var div11 = formGroup.clone();
    div1.append(div11);

    var roomId = input.clone()
            .attr({
                type: 'text',
                placeholder: 'Lokale ID',
                name: 'room-' + counter
            });
    div11.append(roomId);

    var div12 = formGroup.clone();
    div1.append(div12);
    div12.text('Har der været skade i lokalet? ');

    div12.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'yes',
                        name: 'damradio-' + counter
                    })
                    .removeClass('form-control'))
            .append('Ja'));

    div12.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'no',
                        name: 'damradio-' + counter
                    })
                    .removeClass('form-control'))
            .append('Nej'));

    var div13 = $(document.createElement('div')).addClass('row');
    div1.append(div13);

    var div131 = $(document.createElement('div')).addClass('col-md-5');
    div13.append(div131);

    var div1311 = formGroup.clone();
    div131.append(div1311);

    div1311.text('Hvornår?');
    div1311.append(input.clone()
            .attr({
                type: 'date',
                name: 'date-' + counter
            }));

    var div132 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div13.append(div132);

    var div1321 = formGroup.clone();
    div132.append(div1321);
    div1321.text('Hvor?');
    div1321.append(input.clone()
            .attr({
                type: 'text',
                name: 'location-' + counter
            }));

    var div133 = $(document.createElement('div')).addClass('col-md-5');
    div13.append(div133);

    var div1331 = formGroup.clone();
    div133.append(div1331);

    div1331.text('Hvad er der sket?');
    div1331.append(input.clone()
            .attr({
                type: 'text',
                name: 'incident-' + counter
            }));


    var div134 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div13.append(div134);

    var div1341 = formGroup.clone();
    div134.append(div1341);

    div1341.text('Hvad er repareret?');
    div1341.append(input.clone()
            .attr({
                type: 'text',
                name: 'repair-' + counter
            }));

    var div14 = formGroup.clone();
    div1.append(div14);

    div14.text('Skadetype?');
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'moisture-' + counter
                    })
                    .removeClass('form-control'))
            .append('Fugt'));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'rot-' + counter
                    })
                    .removeClass('form-control'))
            .append('Råd og svamp'));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'mold-' + counter
                    })
                    .removeClass('form-control'))
            .append('Skimmel'));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'fire-' + counter
                    })
                    .removeClass('form-control'))
            .append('Brand'));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'other-' + counter
                    })
                    .removeClass('form-control'))
            .append('Andet'));

    div14.append($(document.createElement('hr')));

    var div145 = formGroup.clone();
    div1.append(div145);

    var array = ["Vægge", "Loft", "Gulv", "Vinduer/døre", "Andet"];

//Create and append select list
    var selectList = document.createElement("select");
    selectList.id = "mySelect";
    div145.append(selectList);
    selectList.class = "form-control";

//Create and append the options
    for (var i = 0; i < array.length; i++) {
        var option = document.createElement("option");
        option.value = array[i];
        option.text = array[i];
        selectList.append(option);
    }
    var div15 = formGroup.clone();
    div1.append(div15);

    div15.text('Bemærkninger:');
    div15.append($(document.createElement('textarea'))
            .attr({
                rows: 2,
                name: 'roomComment-' + counter
            })
            .addClass('form-control'));
    div15.append($(document.createElement('span'))
            .text('Billede: ')
            .append(input.clone()
                    .attr({
                        type: 'file',
                        name: 'image-' + counter
                    })
                    .removeClass('form-control')));



    div15.append($(document.createElement('hr')));

    var div16 = formGroup.clone();
    div1.append(div16);
    div16.text('Er der foretaget fugtscanning? ');

    div16.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'yes',
                        name: 'moistradio-' + counter
                    })
                    .removeClass('form-control'))
            .append('Ja'));

    div16.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'no',
                        name: 'moistradio-' + counter
                    })
                    .removeClass('form-control'))
            .append('Nej'));

    var div17 = $(document.createElement('div')).addClass('row');
    div1.append(div17);

    var div171 = $(document.createElement('div')).addClass('col-md-5');
    div17.append(div171);

    var div1711 = formGroup.clone();
    div171.append(div1711);

    div1711.text('Fugtscanning:');
    div1711.append(input.clone()
            .attr({
                type: 'text',
                name: 'moistureScan-' + counter
            }));

    var div172 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div17.append(div172);

    var div1721 = formGroup.clone();
    div172.append(div1721);
    div1721.text('Målepunkt:');
    div1721.append(input.clone()
            .attr({
                type: 'text',
                name: 'measureSpot-' + counter
            }));

    return room.clone();
}
