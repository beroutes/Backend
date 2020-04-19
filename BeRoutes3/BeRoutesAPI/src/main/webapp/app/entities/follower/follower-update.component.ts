import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFollower, Follower } from 'app/shared/model/follower.model';
import { FollowerService } from './follower.service';

@Component({
  selector: 'jhi-follower-update',
  templateUrl: './follower-update.component.html'
})
export class FollowerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    accepted: []
  });

  constructor(protected followerService: FollowerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ follower }) => {
      this.updateForm(follower);
    });
  }

  updateForm(follower: IFollower): void {
    this.editForm.patchValue({
      id: follower.id,
      accepted: follower.accepted
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const follower = this.createFromForm();
    if (follower.id !== undefined) {
      this.subscribeToSaveResponse(this.followerService.update(follower));
    } else {
      this.subscribeToSaveResponse(this.followerService.create(follower));
    }
  }

  private createFromForm(): IFollower {
    return {
      ...new Follower(),
      id: this.editForm.get(['id'])!.value,
      accepted: this.editForm.get(['accepted'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFollower>>): void {
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
