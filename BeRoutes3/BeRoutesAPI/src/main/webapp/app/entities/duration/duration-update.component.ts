import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDuration, Duration } from 'app/shared/model/duration.model';
import { DurationService } from './duration.service';

@Component({
  selector: 'jhi-duration-update',
  templateUrl: './duration-update.component.html'
})
export class DurationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descriptionDuration: [],
    minutes: [],
    hours: [],
    days: [],
    weeks: [],
    years: []
  });

  constructor(protected durationService: DurationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ duration }) => {
      this.updateForm(duration);
    });
  }

  updateForm(duration: IDuration): void {
    this.editForm.patchValue({
      id: duration.id,
      descriptionDuration: duration.descriptionDuration,
      minutes: duration.minutes,
      hours: duration.hours,
      days: duration.days,
      weeks: duration.weeks,
      years: duration.years
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const duration = this.createFromForm();
    if (duration.id !== undefined) {
      this.subscribeToSaveResponse(this.durationService.update(duration));
    } else {
      this.subscribeToSaveResponse(this.durationService.create(duration));
    }
  }

  private createFromForm(): IDuration {
    return {
      ...new Duration(),
      id: this.editForm.get(['id'])!.value,
      descriptionDuration: this.editForm.get(['descriptionDuration'])!.value,
      minutes: this.editForm.get(['minutes'])!.value,
      hours: this.editForm.get(['hours'])!.value,
      days: this.editForm.get(['days'])!.value,
      weeks: this.editForm.get(['weeks'])!.value,
      years: this.editForm.get(['years'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDuration>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
